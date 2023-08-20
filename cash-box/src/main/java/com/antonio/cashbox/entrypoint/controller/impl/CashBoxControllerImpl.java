package com.antonio.cashbox.entrypoint.controller.impl;

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
        insertCashBoxUseCase.insert(customerId, cashBoxRequest.nameBox(), cashBoxRequest.typeBox());
        return ResponseEntity.status(201).build();
    }

    @Override
    public List<CashBoxResponse> findByCustomerId(String customerId, TypeBox typeBox) {
        final var cashBox = findCashBoxyByIdUseCase.find(customerId, typeBox);
        return cashBoxMapper.toCashBoxResponse(cashBox);
    }

    @Override
    public ResponseEntity<Void> updateAmount(String customerId, String nameBox, BigDecimal amount) {
        updateCashBoxUseCase.updateAmount(customerId, nameBox, amount);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> updateAmountBlocked(String customerId, String nameBox, BigDecimal amountBlocked) {
        updateCashBoxUseCase.updateAmountBlocked(customerId, nameBox, amountBlocked);
        return ResponseEntity.ok().build();
    }

}
