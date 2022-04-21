package com.example.user.service;

import com.example.user.dto.UserDTO;
import com.example.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Page<User> getAllUsers(Pageable page);

    Optional<User> getUserById(Long id);

    Optional<User> getUserByEmail(String email);

    List<User> createUser(UserDTO userDTO);

    List<User> updateFirstNameById(Long id, UserDTO userDTO);

    void deleteUserById(Long id);

    List<User> updateLastNameById(Long id, UserDTO userDTO);

    List<User> updateGenderById(Long id, UserDTO userDTO);

    List<User> updateEmailById(Long id, UserDTO userDTO);

    List<User> updateUsernameById(Long id, UserDTO userDTO);
}
