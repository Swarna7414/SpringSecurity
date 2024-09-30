package com.Securtiy.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sec")
public class CustomController {

    @GetMapping("/love")
    public String getlove(){
        return "I Love You";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/pre")
    public String getpre(){
        return "Preethi loving someone";
    }

}