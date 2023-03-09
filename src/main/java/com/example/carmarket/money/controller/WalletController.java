package com.example.carmarket.money.controller;

import com.example.carmarket.money.service.WalletService;
import com.example.carmarket.user.dto.FullUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/money")
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;

    @GetMapping
    public String getTotalWallet(@AuthenticationPrincipal FullUserDto fullUserDto, Map<String, Object> model) {
        String total = walletService.getTotalWallet(fullUserDto.getId());
        model.put("total", total);
        return "wallet";
    }

    @PostMapping("/add")
    public String addMoney(@AuthenticationPrincipal FullUserDto fullUserDto, @RequestParam Long total) {
        walletService.addMoney(fullUserDto, total);
        return "redirect:/money";
    }
}
