package com.training.emelyanenko.converter;

import com.training.emelyanenko.domain.User;
import com.training.emelyanenko.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserConverter extends AbstractConverter<User, UserDto> {

    @Override
    Class<UserDto> getDomainClass() {
        return UserDto.class;
    }

    @Override
    Class<User> getEntityClass() {
        return User.class;
    }
}
