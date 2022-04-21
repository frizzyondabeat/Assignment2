package com.example.user.controller;

import com.example.user.dto.UserDTO;
import com.example.user.exceptions.ApiNotFound;
import com.example.user.model.User;
import com.example.user.service.UserServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.example.user.controller.UserManagementController.getListResponseEntity;
import static com.example.user.controller.UserManagementController.getPageResponseEntity;

@RestController
@RequestMapping(path = "api/users")
@Slf4j
public class UserController {

    public final UserServiceImp userServiceImp;

    @Autowired
    public UserController(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @GetMapping
    public ResponseEntity<Page<User>> getAllUsers(
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC, size = 5) Pageable page) {
        return getPageResponseEntity(page, log, userServiceImp);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable (value = "id") Long id){
        try {
            log.info("Attempting to get user with id {} .....", id);
            Optional<User> userById = userServiceImp.getUserById(id);
            return new ResponseEntity<>(userById, HttpStatus.FOUND);
        } catch (ApiNotFound notFoundException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "email/{email}")
    public ResponseEntity<Optional<User>> getUserById(
            @PathVariable (value = "email") String email){
        try {
            log.info("Attempting to get user with email {} .....", email);
            Optional<User> userById = userServiceImp.getUserByEmail(email);
            return new ResponseEntity<>(userById, HttpStatus.FOUND);
        } catch (ApiNotFound notFoundException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<List<User>> createUser(@RequestBody UserDTO userDTO){
        return getListResponseEntity(userDTO, log, userServiceImp);
    }

    @PatchMapping(path = "first_name/{id}")
    public ResponseEntity<List<User>> updateFirstNameById(@PathVariable (name = "id") Long id, @RequestBody UserDTO userDTO) {
        try {
            log.info("Attempting to update firstname at id {} .....", id);
            List<User> updatedUser = userServiceImp.updateFirstNameById(id, userDTO);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (ApiNotFound notFoundException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping(path = "last_name/{id}")
    public ResponseEntity<List<User>> updateLastNameById(@PathVariable (name = "id") Long id, @RequestBody UserDTO userDTO) {
        try {
            log.info("Attempting to update lastname at id {} .....", id);
            List<User> updatedUser = userServiceImp.updateLastNameById(id, userDTO);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (ApiNotFound notFoundException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping(path = "gender/{id}")
    public ResponseEntity<List<User>> updateGenderById(@PathVariable (name = "id") Long id, @RequestBody UserDTO userDTO) {
        try {
            log.info("Attempting to update gender at id {} .....", id);
            List<User> updatedUser = userServiceImp.updateGenderById(id, userDTO);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (ApiNotFound notFoundException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping(path = "email/{id}")
    public ResponseEntity<List<User>> updateEmailById(@PathVariable (name = "id") Long id, @RequestBody UserDTO userDTO) {
        try {
            log.info("Attempting to update email at id {} .....", id);
            List<User> updatedUser = userServiceImp.updateEmailById(id, userDTO);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (ApiNotFound notFoundException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping(path = "username/{id}")
    public ResponseEntity<List<User>> updateUsernameById(@PathVariable (name = "id") Long id, @RequestBody UserDTO userDTO) {
        try {
            log.info("Attempting to update username at id {} .....", id);
            List<User> updatedUser = userServiceImp.updateUsernameById(id, userDTO);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (ApiNotFound notFoundException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
