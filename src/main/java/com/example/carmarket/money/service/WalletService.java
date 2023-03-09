package com.example.carmarket.money.service;

import com.example.carmarket.money.dto.MoneyMovementDto;
import com.example.carmarket.money.model.FinancialTransaction;
import com.example.carmarket.money.model.MoneyMovement;
import com.example.carmarket.money.repo.WalletRepo;
import com.example.carmarket.user.dto.FullUserDto;
import com.example.carmarket.user.mapper.UserMapper;
import com.example.carmarket.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepo walletRepo;
    private final UserMapper userMapper;

    public String getTotalWallet(Long userId) {
        Long total = walletRepo.getTotalByUserIdAndTime(userId, LocalDateTime.now()); // тут копейки
        return total == 0 ? "0" : String.valueOf(total / 100);
    }

    public boolean addMoney(FullUserDto fullUserDto, Long total) {
        MoneyMovement moneyMovement = new MoneyMovement();
        moneyMovement.setTime(LocalDateTime.now());
        moneyMovement.setUser(userMapper.toUserEntity(fullUserDto));
        moneyMovement.setOperation(FinancialTransaction.INCOME);
        moneyMovement.setTotal(total * 100);

        walletRepo.save(moneyMovement);

        return true;
    }

}
