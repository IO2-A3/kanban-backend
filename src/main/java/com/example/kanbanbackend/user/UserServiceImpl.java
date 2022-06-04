package com.example.kanbanbackend.user;

import com.example.kanbanbackend.exceptions.IncorrectIdInputException;
import com.example.kanbanbackend.exceptions.IncorrectInputDataException;
import com.example.kanbanbackend.user.models.*;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDataValidator userDataValidator;
    private final ModelMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findUserByUsername(username).orElseThrow(() -> new IncorrectInputDataException("Wrong username"));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }

    @Override
    public void addUser(UserServiceCommand command) {
        userDataValidator.validateUserWebInput(command.getWebInput());

        var password = passwordEncoder.encode(command.getWebInput().getPassword());
        var timestamp = new Timestamp(System.currentTimeMillis());

        var user = User.builder()
                .email(command.getWebInput().getEmail())
                .username(command.getWebInput().getUsername())
                .password(password)
                .createdAt(timestamp)
                .id(UUID.randomUUID()).build();

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
    public void deleteAnUser(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public String getUserName(UUID id) {
        var user = getUser(id);
        return user.getUsername();
    }

    @Override
    public UserIdDto getUserById(UUID id) {
        var user = getUser(id);
        return mapper.map(user, UserIdDto.class);
    }

    @Override
    public void updateAnUser(UUID id, UserUpdateWebInput input) {
        var user = getUser(id);

        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        mapper.map(input, user);

        userRepository.save(user);
    }

    public UserPublicDTO getUserByUsername(String username) {
        User user = userRepository.findUserByUsername(username).orElseThrow(() -> new IncorrectInputDataException("User with given username doesn't exist"));
        return mapper.map(user, UserPublicDTO.class);
    }

    private User getUser(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new IncorrectIdInputException("Wrong id!"));
    }
}
