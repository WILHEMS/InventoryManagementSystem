package com.example.inventorymanagementservice.components.presentation.controllers;

import com.example.inventorymanagementservice.components.business.services.ReportService;
import com.example.inventorymanagementservice.components.business.services.UserManagementService;
import com.example.inventorymanagementservice.components.presentation.response_bodies.implementation.user.*;
import com.example.inventorymanagementservice.components.presentation.response_bodies.interfaces.UserResponse;
import com.example.inventorymanagementservice.components.persistence.entities.User;
import com.example.inventorymanagementservice.components.presentation.request_bodies.user.UserDeleteRequestBody;
import com.example.inventorymanagementservice.components.presentation.request_bodies.user.UserLoginRequestBody;
import com.example.inventorymanagementservice.components.presentation.request_bodies.user.UserUpdateRequestBody;
import net.sf.jasperreports.engine.JRException;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
//import java.util.logging.Logger;
import java.io.IOException;
import java.util.logging.*;


@RestController
public class UserController {

    //private final static Logger logger = (Logger) LoggerFactory.getLogger(UserController.class);
    private static Logger logger =  LoggerFactory.getLogger(UserController.class);

    private UserManagementService userManagementService;
    @Autowired
    private ReportService reportService;

    public UserController(UserManagementService userManagementService, ReportService reportService) {
        this.userManagementService = userManagementService;
        this.reportService = reportService;
    }

    /**
     * Register a user.
     * The request body must contain the following properties;
     * <em>username</em> - the username of the user.
     * <em>first_name</em> - the first name of the user.
     * <em>last_name</em> - the last name of the user.
     * <em>role</em> - the role of the user.
     * <em>street</em> - the street address.
     * <em>city</em> - the city address.
     * <em>country</em> - the country address.
     * <em>password</em> - the user password.
     * <em>profile_picture</em> - The profile picture of the user as bytes.
     * @param requestBody the body of the request.*/
    @PostMapping(value = "user/register", consumes = {"application/json", "application/octet-stream"}, produces = {
            "application/json"})
    @CrossOrigin(methods = {RequestMethod.POST})
    public ResponseEntity<UserRegistrationResponse> register(@RequestBody User requestBody){
        logger.info("User Registration Process started");

        UserRegistrationResponse registrationResponse = new UserRegistrationResponse();

        if (!userManagementService.register(requestBody)){
            logger.warn("User request to be registered already exists");
            registrationResponse.setMessage("user exists");
            return new ResponseEntity<>(registrationResponse, HttpStatus.OK);
        }
        logger.info("User registered successfully");

        registrationResponse.setMessage("accepted");
        return new ResponseEntity<>(registrationResponse, HttpStatus.OK);
    }


    /**
     * User login.
     * The Request body must contain the properties;
     * <em>username</em> - the username of the user.
     * <em>password</em> - the password of the user.
     * @param requestBody the body of the request.*/
    /*allowed exposed endpoint and request method*/
    @PostMapping(value = "user/login", consumes = {"application/json", "plain/text"}, produces = {"application/json"})
    @CrossOrigin(methods = {RequestMethod.POST})
    public ResponseEntity<UserLoginResponse> login(@RequestBody UserLoginRequestBody requestBody){

        UserLoginResponse loginResponse= new UserLoginResponse();
        User user = userManagementService.login(requestBody);

        if (user == null){

            loginResponse.setMessage("no such user");
            loginResponse.setUser(null);
            return new ResponseEntity<>(loginResponse, HttpStatus.OK);

        }

        loginResponse.setMessage("success");
        loginResponse.setUser(user);
        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }
    //@PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = "user/fetch", produces = {"application/json"})
    @CrossOrigin(methods = {RequestMethod.GET})
    public ResponseEntity<UserFetchResponse> fetch(@RequestParam(value = "username") String username){
        UserFetchResponse fetchResponse = new UserFetchResponse();
        User user = userManagementService.fetch(username);

        if (user == null){

            fetchResponse.setMessage("no such user");
            fetchResponse.setUser(null);

            return new ResponseEntity<>(fetchResponse, HttpStatus.OK);
        }

        fetchResponse.setMessage("found");
        fetchResponse.setUser(user);

        return new ResponseEntity<>(fetchResponse, HttpStatus.OK);
    }

    @PutMapping(value = "user/update", consumes = {"application/json", "text/plain"}, produces = {"application/json"})
    @CrossOrigin(methods = {RequestMethod.PUT})
    public ResponseEntity<UserUpdateResponse> update(@RequestBody UserUpdateRequestBody requestBody){

        UserUpdateResponse updateResponse = new UserUpdateResponse();

        User user = this.userManagementService.update(requestBody);

        if (user == null){

            updateResponse.setMessage("attempt failed, please check your credentials");
            updateResponse.setUser(null);
            return new ResponseEntity<>(updateResponse, HttpStatus.OK);
        }

        updateResponse.setMessage("updated successfully");
        updateResponse.setUser(user);

        return new ResponseEntity<>(updateResponse, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping(value = "user/delete", consumes = {"application/json", "text/plain"}, produces = {"application/json"})
    @CrossOrigin(methods = {RequestMethod.DELETE})
    public ResponseEntity<UserDeleteResponse> delete(@RequestBody UserDeleteRequestBody requestBody){

        UserDeleteResponse response = new UserDeleteResponse();

        if (!this.userManagementService.delete(requestBody)){

            response.setMessage("failed to delete");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        response.setMessage("successfully deleted");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = {"/user"}, produces = {"application/json"})
    @CrossOrigin(methods = {RequestMethod.GET})
    public ResponseEntity<UserResponse> getUsers(){

        /*define the response*/
        Users userResponse = new Users();

        /*get all users and add them to the response*/
        userResponse.setUsers(this.userManagementService.users());

        /*set the message*/
        userResponse.setMessage("found %s".formatted(userResponse.getUsers().size()));

        /*return the response*/
        return new ResponseEntity<>(userResponse, HttpStatus.OK);

    }
    /**
     * JasperReportGeneration.
     * The Request body must contain the properties;
     * format - the format of the report.
     * @param format-  the body of the request.*/
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/userReport/{format}")
    public ResponseEntity<byte[]> generatedReport(@PathVariable String format) throws JRException, FileNotFoundException {
        byte[] reportBytes = reportService.exportUserReport(format).getBody();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "application/pdf"); // Set the content type to PDF
        responseHeaders.add("Content-Disposition", "attachment; filename=userReport.pdf"); // Force download with a specific filename

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(reportBytes);
    }





   /* @GetMapping("/userReport/{format}")
    public String generatedReport(@PathVariable String format) throws JRException, FileNotFoundException {
        return reportService.exportUserReport(format);
    }*/
}
