package com.example.inventorymanagementservice.components.presentation.controllers;

import com.example.inventorymanagementservice.components.persistence.entities.User;
import com.example.inventorymanagementservice.components.persistence.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.web.exchanges.HttpExchange;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class WelcomePageController {
    @Autowired
    private UserRepository userRepository;;
    @GetMapping("/welcome")
    @ResponseBody
    public String welcome(Principal principal) {
        String swaggerButton = "<form action='/swagger-ui/index.html'><input type='submit' value='IMS ENDPOINTS' style='background-color: #4CAF50; color: white; padding: 10px 20px; font-size: 16px; border: none; cursor: pointer;'></form>";
        String welcomeMessage = "Logged in successfully " + principal.getName();

        User user = userRepository.findUserByUsername(principal.getName());
        if (user != null) {
            welcomeMessage += " as " + user.getRole() + ". Welcome to our system!";
           // return "Logged in successfully " + principal.getName() + ", as " + user.getRole() + ". Welcome to our system!";
        } else {
            welcomeMessage += ", welcome to our system!";
            //return "Logged in successfully " + principal.getName() + ", welcome to our system!";
        }
        return "<html><body>" + welcomeMessage + "<br>" + swaggerButton + "</body></html>";
    }
}
