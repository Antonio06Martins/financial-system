package com.antonio.cashbox.core.usecase.impl;

import com.antonio.cashbox.core.dataprovider.FindCashBoxBy;
import com.antonio.cashbox.core.domain.CashBox;
import com.antonio.cashbox.core.enumeration.StatusBox;
import com.antonio.cashbox.core.enumeration.TypeBox;
import com.antonio.cashbox.core.usecase.FindCashBoxUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class FindCashBoxUseCaseImpl implements FindCashBoxUseCase {

    private final FindCashBoxBy findCashBoxBy;

    @Override
    public List<CashBox> find(String customerId, TypeBox typeBox, StatusBox statusBox) {
        return Optional.ofNullable(findCashBoxBy.findAll(customerId, typeBox, statusBox))
                .orElseThrow(() -> new RuntimeException("Cash Box not found"));
    }
}
