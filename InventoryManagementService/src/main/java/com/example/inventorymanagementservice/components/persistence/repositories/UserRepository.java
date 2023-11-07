package com.example.inventorymanagementservice.components.persistence.repositories;


import com.example.inventorymanagementservice.components.persistence.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;
//import org.springframework.data.rest.core.annotation.RestResource;
//import org.springframework.web.bind.annotation.*;


@Repository
public interface UserRepository extends JpaRepository<User, String > {



    User findUserByUsernameAndPassword(@RequestParam("username") String username, @RequestParam("password") String password);


    User findUserByUsername(@RequestParam("username") String username);

    User findUserByUsernameAndRole(String username, String role);

    void deleteUserByUsername(String username);
    Optional<User> findByUsername(String username);
}
