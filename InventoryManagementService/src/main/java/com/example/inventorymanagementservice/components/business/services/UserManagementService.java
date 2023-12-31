package com.example.inventorymanagementservice.components.business.services;

import com.example.inventorymanagementservice.components.persistence.entities.User;
import com.example.inventorymanagementservice.components.persistence.repositories.AddressRepository;
import com.example.inventorymanagementservice.components.persistence.repositories.UserRepository;
import com.example.inventorymanagementservice.components.presentation.request_bodies.user.UserDeleteRequestBody;
import com.example.inventorymanagementservice.components.presentation.request_bodies.user.UserLoginRequestBody;
import com.example.inventorymanagementservice.components.presentation.request_bodies.user.UserUpdateRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserManagementService {

    private UserRepository userRepository;
    private AddressRepository addressRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserManagementService(UserRepository userRepository, AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }

    public boolean register(User user){

        /*check if the user exists*/
        if (this.userRepository.findUserByUsername(user.getUsername()) != null)
            return false;
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        /*persist the address*/
        this.addressRepository.save(user.getAddress());
        /*persist the user*/
        this.userRepository.save(user);

        return true;
    }

    public User login(UserLoginRequestBody requestBody){

        /*query for the existence of the user*/
        return userRepository.findUserByUsernameAndPassword(requestBody.getUsername(), requestBody.getPassword());
    }

    public User fetch(String username){

        /*query for the user by username*/
        return this.userRepository.findUserByUsername(username);
    }

    @Transactional
    public User update(UserUpdateRequestBody requestBody){

        if (this.userRepository.findUserByUsernameAndPassword(requestBody.getUsername(), requestBody.getPassword()) == null){
            return null;
        }

        User user = requestBody.getUser();

        this.userRepository.deleteUserByUsername(requestBody.getUsername());
        this.addressRepository.save(user.getAddress());
        this.userRepository.save(user);

        return user;
    }

    @Transactional
    public boolean delete(UserDeleteRequestBody requestBody){

        /*delete user*/
        this.userRepository.deleteUserByUsername(requestBody.getUsername());

        return true;
    }

    public List<User> users(){

        List<User> userList = new ArrayList<>();
        this.userRepository.findAll().forEach(userList::add);

        return userList;
    }
}
