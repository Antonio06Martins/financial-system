package com.antonio.cashbox.core.usecase.impl;

import com.antonio.cashbox.core.dataprovider.FindCashBoxBy;
import com.antonio.cashbox.core.dataprovider.InsertCashBox;
import com.antonio.cashbox.core.domain.CashBoxDomain;
import com.antonio.cashbox.core.enumeration.TypeBox;
import com.antonio.cashbox.core.usecase.InsertCashBoxUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class InsertCashBoxUseCaseImpl implements InsertCashBoxUseCase {

    private final InsertCashBox insertCashBox;
    private final FindCashBoxBy findCashBoxBy;

    @Override
    public void insert(String customerId, String nameBox, TypeBox typeBox) {
        final var cashBoxDomain = findCashBoxBy.findUnique(customerId, nameBox);

        if(cashBoxDomain == null) {
            final var defaultCashBox = CashBoxDomain.builder()
                    .nameBox(nameBox)
                    .customerId(customerId)
                    .typeBox(typeBox)
                    .createdIn(LocalDateTime.now())
                    .build();

            insertCashBox.insert(defaultCashBox);
        } else {
            throw new IllegalArgumentException("Já existe");
        }
    }
}
