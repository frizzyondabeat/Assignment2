package com.example.user.service;

import com.example.user.dto.UserDTO;
import com.example.user.exceptions.ApiBadRequest;
import com.example.user.exceptions.ApiNotFound;
import com.example.user.model.User;
import com.example.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImp implements UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Page<User> getAllUsers(Pageable page) {
        return userRepository.findAll(page);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        if (!userRepository.existsById(id)){
            log.error("No such user");
            throw new ApiNotFound("User with " + id + " not found");
        }
        log.info("User {} found", id);
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        if (!userRepository.existsByEmail(email)){
            log.error("No such user");
            throw new ApiNotFound("User with email: " + email + " not found");
        }
        log.info("User with email {} found", email);
        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public List<User> createUser(UserDTO userDTO) {
        if (userRepository.existsByUsername(userDTO.getUsername())){
            log.error("User already exists!");
            throw new ApiBadRequest("User already exists");
        }
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setGender(userDTO.getGender());
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setDateCreated(Timestamp.valueOf(LocalDateTime.now()));
        user.setLastUpdated(Timestamp.valueOf(LocalDateTime.now()));
        user.setLastLogin(null);
        userRepository.save(user);
        log.info("User with username: {} successful created", user.getUsername());
        return List.of(user);
    }

    @Override
    @Transactional
    public List<User> updateFirstNameById(Long id, UserDTO userDTO) {
        if (userRepository.findById(id).isEmpty() || userDTO.getFirstName().isEmpty()){
            log.error("Invalid update for id {}", id);
            throw new ApiBadRequest("Bad request");
        }
        User user = userRepository.getById(id);
        user.setFirstName(userDTO.getFirstName());
        user.setLastUpdated(Timestamp.valueOf(LocalDateTime.now()));
        userRepository.save(user);
        log.info("User's firstname at id {} has been updated", id);
        return List.of(userRepository.getById(id));
    }

    @Override
    @Transactional
    public List<User> updateLastNameById(Long id, UserDTO userDTO) {
        if (userRepository.findById(id).isEmpty() || userDTO.getLastName().isEmpty()){
            log.error("Bad last name update request for id {}", id);
            throw new ApiBadRequest("Request invalid");
        }
        User user = userRepository.getById(id);
        user.setLastName(userDTO.getLastName());
        user.setLastUpdated(Timestamp.valueOf(LocalDateTime.now()));
        userRepository.save(user);
        log.info("User's lastname at id {} has been updated", id);
        return List.of(userRepository.getById(id));
    }

    @Override
    @Transactional
    public List<User> updateGenderById(Long id, UserDTO userDTO) {
        if (userRepository.findById(id).isEmpty() || userDTO.getGender().isEmpty()){
            log.error("Invalid gender update request for id {}", id);
            throw new ApiBadRequest("Invalid gender update request");
        }
        User user = userRepository.getById(id);
        user.setGender(userDTO.getGender());
        user.setLastUpdated(Timestamp.valueOf(LocalDateTime.now()));
        userRepository.save(user);
        log.info("User's gender at id {} has been updated", id);
        return List.of(userRepository.getById(id));
    }

    @Override
    @Transactional
    public List<User> updateEmailById(Long id, UserDTO userDTO) {
        if (userRepository.findById(id).isEmpty() || userDTO.getEmail().isEmpty()){
            log.error("Invalid email update request for id {}", id);
            throw new ApiBadRequest("Invalid email update request");
        }
        User user = userRepository.getById(id);
        user.setEmail(userDTO.getEmail());
        user.setLastUpdated(Timestamp.valueOf(LocalDateTime.now()));
        userRepository.save(user);
        log.info("User's email at id {} has been updated", id);
        return List.of(userRepository.getById(id));
    }

    @Override
    @Transactional
    public List<User> updateUsernameById(Long id, UserDTO userDTO) {
        if (userRepository.findById(id).isEmpty() || userDTO.getUsername().isEmpty()){
            log.error("Invalid username update request for id {}", id);
            throw new ApiBadRequest("Invalid username update request");
        }
        User user = userRepository.getById(id);
        user.setUsername(userDTO.getUsername());
        user.setLastUpdated(Timestamp.valueOf(LocalDateTime.now()));
        userRepository.save(user);
        log.info("User's username at id {} has been updated", id);
        return List.of(userRepository.getById(id));
    }

    @Override
    @Transactional
    public void deleteUserById(Long id) {
        if (!userRepository.existsById(id)){
            log.error("No user to delete in id {}", id);
            throw new ApiBadRequest("Invalid user");
        }
        userRepository.deleteById(id);
    }
}
