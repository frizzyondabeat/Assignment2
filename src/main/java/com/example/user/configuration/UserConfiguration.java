package com.example.user.configuration;

import com.example.user.model.User;
import com.example.user.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class UserConfiguration {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository){
        return args -> {
            User user = new User();
            user.setFirstName("Efeoghene");
            user.setLastName("Omonigho");
            user.setGender("Male");
            user.setEmail("omonigs@gmail.com");
            user.setUsername("frizzyondabeat");
            user.setPassword("tomriddle");
            user.setDateCreated(Timestamp.valueOf(LocalDateTime.now()));
            user.setLastUpdated(Timestamp.valueOf(LocalDateTime.now()));

            User user2 = new User();
            user2.setFirstName("Joshua");
            user2.setLastName("Omonigho");
            user2.setGender("Male");
            user2.setEmail("jayrush@gmail.com");
            user2.setUsername("jayrush");
            user2.setPassword("jayrush");
            user2.setDateCreated(Timestamp.valueOf(LocalDateTime.now()));
            user2.setLastUpdated(Timestamp.valueOf(LocalDateTime.now()));
            userRepository.saveAll(List.of(user, user2));

        };
    }
}
