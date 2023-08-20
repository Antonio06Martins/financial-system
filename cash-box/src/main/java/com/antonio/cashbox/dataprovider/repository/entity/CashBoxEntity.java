package com.antonio.cashbox.dataprovider.repository.entity;

import com.antonio.cashbox.core.enumeration.TypeBox;
import lombok.Builder;;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Document(collection = "cash-box")
public record CashBoxEntity (
        @Id
        String id,
        String customerId,
        String nameBox,
        TypeBox typeBox,
        LocalDateTime createdIn,
        SelectBlockedBalanceEntity selectBlockedBalance,
        SelectBalanceEntity selectBalance) {

        @Builder
        public record SelectBalanceEntity(
                BigDecimal accountBalance,
                LocalDateTime lastUpdate) {
        }

        @Builder
        public record SelectBlockedBalanceEntity(
                BigDecimal amountBlocked,
                LocalDateTime lastUpdate) {
        }


}
