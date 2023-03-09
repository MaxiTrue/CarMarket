package com.example.carmarket.money.dto;

import com.example.carmarket.money.model.FinancialTransaction;
import com.example.carmarket.user.dto.FullUserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MoneyMovementDto {
    private Long id;
    private FullUserDto fullUserDto;
    private FinancialTransaction operation = FinancialTransaction.INCOME;
    private LocalDateTime time = LocalDateTime.now();
    private Long total; // храним в копейках
}
