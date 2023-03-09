package com.example.carmarket.money.repo;

import com.example.carmarket.money.model.MoneyMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface WalletRepo extends JpaRepository<MoneyMovement, Long> {

    @Query("select coalesce(sum(mm.total), 0) from MoneyMovement mm where mm.user.id = :userId and mm.time <= :moment")
    Long getTotalByUserIdAndTime(Long userId, LocalDateTime moment);
}
