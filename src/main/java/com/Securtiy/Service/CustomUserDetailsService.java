package com.Securtiy.Service;

import com.Securtiy.Model.CustomUser;
import com.Securtiy.Repository.CustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    
    @Autowired
    private CustomRepository repository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        CustomUser user=repository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("The User Was not founded here"));
        return User.withUsername(user.getEmail()).password(user.getPassword()).roles(user.getRole()).build();
    }
}
