
package com.Securtiy.Configuration;

import com.Securtiy.Service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> {
            requests.requestMatchers("/h2-console/**").permitAll().
                    requestMatchers("/security/registeruser").permitAll()
                    .requestMatchers("/security/user").hasRole("USER")
                    .requestMatchers("/security/admin").hasRole("ADMIN")
                    .anyRequest().authenticated();
        });
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());
        http.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));
        http.csrf(csrf -> csrf.disable());
        return (SecurityFilterChain) http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception{
        AuthenticationManagerBuilder authenticationManagerBuilder=http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }


}


/// This is the doe for Using IN Auth Memore : )
/*
    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails user= User.withUsername("user").password(passwordEncoder().encode("user")).roles("USER").build();

        UserDetails user1= User.withUsername("user1").password(passwordEncoder().encode("user1")).roles("USER").build();

        UserDetails admin= User.withUsername("admin").password(passwordEncoder().encode("admin")).roles("ADMIN").build();

        UserDetails admin1= User.withUsername("admin1").password(passwordEncoder().encode("admin1A")).roles("ADMIN").build();

       JdbcUserDetailsManager userDetailsManager=new JdbcUserDetailsManager(dataSource);

        userDetailsManager.createUser(user);
        userDetailsManager.createUser(user1);
        userDetailsManager.createUser(admin);
        userDetailsManager.createUser(admin1);

        return userDetailsManager;
    }
*/