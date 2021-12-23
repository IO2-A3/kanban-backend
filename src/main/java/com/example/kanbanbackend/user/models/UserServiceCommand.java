package com.example.kanbanbackend.user.models;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserServiceCommand {
    private UserWebInput webInput;
}
