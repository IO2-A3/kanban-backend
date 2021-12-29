package com.example.kanbanbackend.user;

import com.example.kanbanbackend.UI.idGenerator.IdGenerator;
import com.example.kanbanbackend.exceptions.IncorrectIdInputException;
import com.example.kanbanbackend.user.models.*;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDataValidator userDataValidator;
    private final IdGenerator idGenerator;
    private final ModelMapper mapper;

    @Override
    public void addUser(UserServiceCommand command) {
        userDataValidator.validateUserWebInput(command.getWebInput());

        var id = idGenerator.generateId();
        var password = passwordEncoder.encode(command.getWebInput().getPassword());
        var timestamp = new Timestamp(System.currentTimeMillis());

        var user = User.builder()
                .email(command.getWebInput().getEmail())
                .firstName(command.getWebInput().getFirstName())
                .lastName(command.getWebInput().getLastName())
                .username(command.getWebInput().getUsername())
                .password(password)
                .createdAt(timestamp)
                .id(id).build();

        userRepository.save(user);
    }

    @Override
    public List<UserListDto> getUsers() {
        var users = userRepository.findAll();

        return users.stream()
                .map(user -> mapper.map(user, UserListDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAnUser(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public String getUserName(String id) {
        var user = getUser(id);
        return user.getUsername();
    }

    @Override
    public UserIdDto getUserById(String id) {
        var user = getUser(id);
        return mapper.map(user, UserIdDto.class);
    }

    @Override
    public void updateAnUser(String id, UserUpdateWebInput input) {
        var user = getUser(id);

        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        mapper.map(input, user);

        userRepository.save(user);
    }

    private User getUser(String id) {
        return userRepository.findById(id).orElseThrow(() -> new IncorrectIdInputException("Wrong id!"));
    }
}
