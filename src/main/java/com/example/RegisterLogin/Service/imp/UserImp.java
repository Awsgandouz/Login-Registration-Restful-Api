package com.example.RegisterLogin.Service.imp;

import com.example.RegisterLogin.Dto.LoginDTO;
import com.example.RegisterLogin.Dto.UserDTO;
import com.example.RegisterLogin.Entity.User;
import com.example.RegisterLogin.Repository.UserRepo;
import com.example.RegisterLogin.Service.UserService;
import com.example.RegisterLogin.payload.response.LoginMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserImp implements UserService {
    @Autowired
    public  UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String addUser(UserDTO userDTO) {
        User user = new User(
                userDTO.getId() ,
                userDTO.getFirst_name(),
                userDTO.getLast_name(),
                userDTO.getEmail(),
                this.passwordEncoder.encode(userDTO.getPassword())
        );
        userRepo.save(user);
        return user.getFirst_name();
    }

    @Override
    public LoginMessage loginUser(LoginDTO loginDTO) {
        User user1 = userRepo.findByEmail(loginDTO.getEmail());
        if(user1 != null){
            String password = loginDTO.getPassword();
            String encodedPassword = user1.getPassword();
            Boolean isPwdRight = passwordEncoder.matches(password , encodedPassword);
            if(isPwdRight){
                Optional<User> user =  userRepo.findOneByEmailAndPassword(loginDTO.getEmail() , encodedPassword);
                if(user.isPresent()){
                    return new LoginMessage("Login Sucessful" , true);
                }else {
                    return new LoginMessage("Login Failed" , false);
                }
            }else{
                return new LoginMessage("Password not match" , false);
            }
        }else {
            return new LoginMessage("Email not exists" , false);
        }
    }

}
