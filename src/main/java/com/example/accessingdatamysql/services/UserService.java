package com.example.accessingdatamysql.services;

import com.example.accessingdatamysql.DTO.users.UserDTO;
import com.example.accessingdatamysql.database.entities.User;
import com.example.accessingdatamysql.database.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserDTO findUserByEmail(String email) {
        User user = userRepository.findByEmail(email);

        if (user != null) {
            return new UserDTO(user.id, user.name, user.email);
        }

        return null;
    }

    public Optional<UserDTO> saveUser(String name, String email) {
        Optional<User> existingUser = Optional.ofNullable(userRepository.findByEmail(email));
        if (existingUser.isPresent()) {
            return Optional.of(new UserDTO(existingUser.get().id, existingUser.get().name, existingUser.get().email));
        } else {
            User user = new User();
            user.name = name;
            user.email = email;
            userRepository.save(user);

            user = userRepository.findByEmail(email);

            return Optional.of(new UserDTO(user.id, user.name, user.email));
        }
    }

    public UserDTO updateUser(UserDTO userDto, String name, String email) {
        User user = userRepository.findById(Math.toIntExact(userDto.getId())).orElseThrow(() -> new RuntimeException("User not found"));

        user.name = name;
        user.email = email;
        userRepository.save(user);

        return new UserDTO(user.id, user.name, user.email);
    }

    public void deleteUser(String email) {
        User user = userRepository.findByEmail(email);

        if (user != null) {
            userRepository.delete(user);
        }
    }

}
