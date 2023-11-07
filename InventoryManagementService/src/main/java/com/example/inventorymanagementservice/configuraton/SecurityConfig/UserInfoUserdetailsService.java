package com.example.inventorymanagementservice.configuraton.SecurityConfig;

import com.example.inventorymanagementservice.components.persistence.entities.User;
import com.example.inventorymanagementservice.components.persistence.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserInfoUserdetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userInformation = userRepository.findByUsername(username);

        //converting user information from db into userdetails object by giving username, password, and authority

        return userInformation.map(UserInfoUserDetails::new)
                .orElseThrow(()->new UsernameNotFoundException("user not found " + username));
    }

}
