package com.example.kanbanbackend.security;

import com.example.kanbanbackend.exceptions.IncorrectInputDataException;
import com.example.kanbanbackend.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@AllArgsConstructor
@Service
public class IdUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        var user = userRepository.findById(id).orElseThrow(() -> new IncorrectInputDataException("Wrong id"));
        return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }
}