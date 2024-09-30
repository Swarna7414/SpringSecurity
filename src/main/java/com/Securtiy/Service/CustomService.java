package com.Securtiy.Service;

import com.Securtiy.Controller.CustomController;
import com.Securtiy.DTO.CustomUserDTO;
import com.Securtiy.Exception.alReadyUserPresentException;
import com.Securtiy.Model.CustomUser;
import com.Securtiy.Repository.CustomRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomService {

    @Autowired
    CustomRepository customRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    private static final Logger logger= LoggerFactory.getLogger(CustomService.class);

    public String registerFirsttimeUser(CustomUserDTO user){
        CustomUser customUser=new CustomUser();
        customUser.setEmail(user.getEmail());
        customUser.setPassword(passwordEncoder.encode(user.getPassword()));
        customUser.setFirstname(user.getFirstname());
        customUser.setLastname(user.getLastname());
        customUser.setAddress(user.getAddress());
        customUser.setAge(user.getAge());
        customUser.setPhonenumber(user.getPhonenumber());
        customUser.setCity(user.getCity());
        customUser.setState(user.getState());
        customUser.setPincode(user.getPincode());
        if(customRepository.findByEmail(user.getEmail()).isPresent()){
            throw new alReadyUserPresentException("Email is already registered");
        }
        customRepository.save(customUser);
        return "Regestration Sucssfully Completed";
    }

}
