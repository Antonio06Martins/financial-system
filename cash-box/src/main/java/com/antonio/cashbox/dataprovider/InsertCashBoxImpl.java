package com.antonio.cashbox.dataprovider;

import com.antonio.cashbox.core.dataprovider.InsertCashBox;
import com.antonio.cashbox.core.domain.CashBox;
import com.antonio.cashbox.dataprovider.repository.CashBoxRepository;
import com.antonio.cashbox.dataprovider.repository.mapper.CashBoxEntityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class InsertCashBoxImpl implements InsertCashBox {

    private final CashBoxRepository cashBoxRepository;

    private final CashBoxEntityMapper cashBoxEntityMapper;

    @Override
    public void insert(CashBox cashBox) {
        var cashBoxEntity = cashBoxEntityMapper.toCashBoxEntity(cashBox);
        cashBoxRepository.save(cashBoxEntity);
    }
}
