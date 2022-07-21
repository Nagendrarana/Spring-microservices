package com.database.database.services;

import com.database.database.model.CustomUserDetails;
import com.database.database.model.Users;
import com.database.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsServices implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users users = this.userRepository.findByUsername(username);
        if(users==null){
            throw new UsernameNotFoundException("No User");
        }
        else{
            return new CustomUserDetails(users);
        }

    }
}
