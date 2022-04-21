package com.example.user.controller;

import com.example.user.dto.UserDTO;
import com.example.user.exceptions.ApiBadRequest;
import com.example.user.exceptions.ApiNotFound;
import com.example.user.model.User;
import com.example.user.service.UserServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "management/api/users")
public class UserManagementController {

    private final UserServiceImp userServiceImp;

    @Autowired
    public UserManagementController(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @GetMapping
    public ResponseEntity<Page<User>> getAllUsers(
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC, size = 5) Pageable page) {
        return getPageResponseEntity(page, log, userServiceImp);
    }

    static ResponseEntity<Page<User>> getPageResponseEntity(@PageableDefault(sort = "id", direction = Sort.Direction.ASC, size = 5) Pageable page, Logger log, UserServiceImp userServiceImp) {
        try {
            log.info("Attempting to get all users.....");
            Page<User> userList = userServiceImp.getAllUsers(page);
            return new ResponseEntity<>(userList, HttpStatus.OK);}
        catch (Exception exception) {
            log.error("Error getting users!");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<List<User>> createUser(@RequestBody UserDTO userDTO){
        return getListResponseEntity(userDTO, log, userServiceImp);
    }

    static ResponseEntity<List<User>> getListResponseEntity(@RequestBody UserDTO userDTO, Logger log, UserServiceImp userServiceImp) {
        try {
            log.info("Attempting to create new user.....");
            List<User> createdUser = userServiceImp.createUser(userDTO);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (ApiBadRequest exception){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable (name = "id") Long id){
        try {
            log.info("Attempting to delete stock with id {} .....", id);
            userServiceImp.deleteUserById(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (ApiNotFound notFoundException){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
