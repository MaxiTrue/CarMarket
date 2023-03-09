package com.example.carmarket;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.Map;

@Controller
public class MainController {

    @GetMapping
    public String greeting(Map<String, Object> model)  {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model)  {
        model.put("some", "It's main page");
        return "main";
    }

}
