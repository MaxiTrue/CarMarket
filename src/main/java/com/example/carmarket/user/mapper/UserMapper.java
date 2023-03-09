package com.example.carmarket.user.mapper;

import com.example.carmarket.user.dto.FullUserDto;
import com.example.carmarket.user.dto.NewUserDto;
import com.example.carmarket.user.model.Role;
import com.example.carmarket.user.model.User;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class UserMapper {

    public FullUserDto toFullUserDto(User user){
        return FullUserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .active(Boolean.TRUE)
                .roles(user.getRoles()).build();
    }

    public User toUserEntity(NewUserDto newUserDto) {
        User user = new User();
        user.setUsername(newUserDto.getUsername());
        user.setPassword(newUserDto.getPassword());
        user.setActive(newUserDto.getActive());
        user.setRoles(Set.of(Role.USER)); // пока одна роль, и так сойдёт
        return user;
    }

    public User toUserEntity(FullUserDto fullUserDto) {
        User user = new User();
        user.setId(fullUserDto.getId());
        user.setUsername(fullUserDto.getUsername());
        user.setPassword(fullUserDto.getPassword());
        user.setActive(fullUserDto.getActive());
        user.setRoles(fullUserDto.getRoles());
        return user;
    }


}