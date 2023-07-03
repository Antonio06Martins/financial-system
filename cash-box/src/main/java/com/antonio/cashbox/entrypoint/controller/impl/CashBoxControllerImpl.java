package com.antonio.cashbox.entrypoint.controller.impl;

import com.antonio.cashbox.core.enumeration.StatusBox;
import com.antonio.cashbox.core.enumeration.TypeBox;
import com.antonio.cashbox.core.usecase.FindCashBoxUseCase;
import com.antonio.cashbox.core.usecase.InsertCashBoxUseCase;
import com.antonio.cashbox.core.usecase.UpdateCashBoxUseCase;
import com.antonio.cashbox.entrypoint.controller.CashBoxController;
import com.antonio.cashbox.entrypoint.controller.mapper.CashBoxMapper;
import com.antonio.cashbox.entrypoint.controller.request.CashBoxRequest;
import com.antonio.cashbox.entrypoint.controller.response.CashBoxResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CashBoxControllerImpl implements CashBoxController {

    private final InsertCashBoxUseCase insertCashBoxUseCase;
    private final FindCashBoxUseCase findCashBoxyByIdUseCase;
    private final UpdateCashBoxUseCase updateCashBoxUseCase;
    private final CashBoxMapper cashBoxMapper;
    @Override
    public ResponseEntity<Void> insert(String customerId, CashBoxRequest cashBoxRequest) {
        var cashBox = cashBoxMapper.toCashBox(cashBoxRequest, customerId);
        insertCashBoxUseCase.insert(cashBox);
        return ResponseEntity.ok().build();
    }

    @Override
    public List<CashBoxResponse> findByCustomerId(String customerId, TypeBox typeBox, StatusBox statusBox) {
        var cashBox = findCashBoxyByIdUseCase.find(customerId, typeBox, statusBox);
        return cashBox.stream().map(cashBoxMapper::toCashBoxResponse).collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<Void> update(String customerId, String nameBox, BigDecimal amount, BigDecimal amountBlocked) {
        updateCashBoxUseCase.update(customerId, nameBox, amount, amountBlocked);
        return ResponseEntity.ok().build();
    }
}
