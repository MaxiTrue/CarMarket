package com.example.carmarket.user.service;

import com.example.carmarket.user.dto.NewUserDto;
import com.example.carmarket.user.mapper.UserMapper;
import com.example.carmarket.user.model.User;
import com.example.carmarket.user.repo.UserRepo;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return userMapper.toFullUserDto(user);
    }

    public boolean createUser(NewUserDto newUserDto) {
        Optional<User> user = userRepo.findByUsername(newUserDto.getUsername());

        if(user.isPresent()) {
            return false;
        }

        userRepo.save(userMapper.toUserEntity(newUserDto));
        return true;
    }

}
