package com.Securtiy.Controller;

import com.Securtiy.DTO.CustomUserDTO;
import com.Securtiy.Service.CustomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/security")
public class CustomController {

    @Autowired
    CustomService customService;

    @Autowired
    PasswordEncoder passwordEncoder;

    private static final Logger logger= LoggerFactory.getLogger(CustomController.class);


    @PostMapping("/registeruser")
    public ResponseEntity<Object> RegisterUser(@RequestBody CustomUserDTO user){

        String Message= customService.registerFirsttimeUser(user);

        return ResponseEntity.ok(Message);
    }
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public String getonlyauth(@RequestParam String word){
        return "Hia ra "+word;
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/Admin")
    public String getAdmin(@RequestParam String word){
        return "This is admin"+word;
    }

    @GetMapping("/anyone")
    public String authanyone(@RequestParam String word){
        return "THis is anyone"+word;
    }

}