package com.antonio.cashbox.core.usecase.impl;

import com.antonio.cashbox.core.dataprovider.InsertCashBox;
import com.antonio.cashbox.core.domain.CashBox;
import com.antonio.cashbox.core.enumeration.StatusBox;
import com.antonio.cashbox.core.usecase.InsertCashBoxUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;

@Slf4j
@Component
@RequiredArgsConstructor
public class InsertCashBoxUseCaseImpl implements InsertCashBoxUseCase {

    private final InsertCashBox insertCashBox;

    @Override
    public void insert(CashBox cashBox) {
        var defaultCashBox = CashBox.builder()
                .nameBox(cashBox.getNameBox())
                .customerId(cashBox.getCustomerId())
                .amountBlocked(new BigDecimal(BigInteger.ZERO))
                .accountBalance(new BigDecimal(BigInteger.ZERO))
                .typeBox(cashBox.getTypeBox())
                .statusBox(StatusBox.UNLOCKED)
                .build();

        insertCashBox.insert(defaultCashBox);

    }
}
