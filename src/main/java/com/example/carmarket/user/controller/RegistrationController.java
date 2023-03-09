package com.example.carmarket.user.controller;

import com.example.carmarket.user.dto.NewUserDto;
import com.example.carmarket.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;

    @GetMapping("/registration")
    public String registration (){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@Valid NewUserDto newUserDto, BindingResult bindingResult, Map<String, String> model){

        if(bindingResult.hasErrors()){
            Collector<FieldError, ?, Map<String, String>> collector = Collectors.toMap(
                    fieldError -> fieldError.getField() + "Error",
                    FieldError::getDefaultMessage
            );

            Map<String, String> errors = bindingResult.getFieldErrors().stream().collect(collector);

            //TODO потом переписать нормально
            for (Map.Entry entry: errors.entrySet()){
                model.put((String) entry.getKey(), (String) entry.getValue());
            }

            return "registration";

        } else {
            boolean userCreated = userService.createUser(newUserDto);
            if (!userCreated) {
                model.put("message", "User exist!");
                return "registration";
            }
            return "redirect:/login";
        }
    }
}
