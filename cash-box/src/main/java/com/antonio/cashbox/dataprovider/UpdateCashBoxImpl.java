package com.antonio.cashbox.dataprovider;

import com.antonio.cashbox.core.dataprovider.UpdateCashBox;
import com.antonio.cashbox.core.domain.CashBoxDomain;
import com.antonio.cashbox.dataprovider.repository.CashBoxRepository;
import com.antonio.cashbox.dataprovider.repository.mapper.CashBoxEntityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UpdateCashBoxImpl implements UpdateCashBox {

    private final CashBoxRepository cashBoxRepository;

    private final CashBoxEntityMapper cashBoxEntityMapper;

    @Override
    public void update(CashBoxDomain cashBoxDomain) {
        var cashBoxEntity = cashBoxEntityMapper.toCashBoxEntity(cashBoxDomain);
        cashBoxRepository.save(cashBoxEntity);
    }
}
