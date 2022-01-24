package com.example.kanbanbackend.user;

import com.example.kanbanbackend.email.EmailSenderValidator;
import com.example.kanbanbackend.exceptions.WrongEmailException;
import com.example.kanbanbackend.exceptions.WrongUsernameException;
import com.example.kanbanbackend.user.models.UserWebInput;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserDataValidator {
    private final UserRepository userRepository;

    public void validateUserWebInput(UserWebInput webInput) {
        new EmailSenderValidator().validateEmail(webInput.getEmail());

        checkIfUserWithSuchUsernameExists(webInput.getUsername());
        checkIfUserWithSuchEmailExists(webInput.getEmail());
    }

    private void checkIfUserWithSuchEmailExists(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new WrongEmailException("User with such email already exists!");
        }
    }

    private void checkIfUserWithSuchUsernameExists(String username) {
        if (userRepository.existsByUsername(username)) {
            throw new WrongUsernameException("User with such username already exists!");
        }
    }

    private void checkIfArrOfStringsContainOnlyLetters(String[] names) {
        for (var name :
                names) {
            if (!name.matches("[a-zA-Z]+")) {
                throw new IllegalArgumentException("Invalid arguments!");
            }
        }
    }

}
