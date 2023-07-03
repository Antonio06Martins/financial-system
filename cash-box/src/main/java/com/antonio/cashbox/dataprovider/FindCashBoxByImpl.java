package com.antonio.cashbox.dataprovider;

import com.antonio.cashbox.core.dataprovider.FindCashBoxBy;
import com.antonio.cashbox.core.domain.CashBox;
import com.antonio.cashbox.core.enumeration.StatusBox;
import com.antonio.cashbox.core.enumeration.TypeBox;
import com.antonio.cashbox.dataprovider.repository.CashBoxRepository;
import com.antonio.cashbox.dataprovider.repository.entity.CashBoxEntity;
import com.antonio.cashbox.dataprovider.repository.mapper.CashBoxEntityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class FindCashBoxByImpl implements FindCashBoxBy {

    private final CashBoxRepository cashBoxRepository;

    private final CashBoxEntityMapper cashBoxEntityMapper;

    @Override
    public List<CashBox> findAll(String customerId, TypeBox typeBox, StatusBox statusBox) {
        List<CashBoxEntity> cashBoxEntity = null;
        if(statusBox == null) {
            cashBoxEntity = cashBoxRepository.findByCustomerIdAndTypeBox(customerId, typeBox);
        } else {
            cashBoxEntity = cashBoxRepository.findByCustomerIdAndTypeBoxAndStatusBox(customerId, typeBox, statusBox);
        }

        return cashBoxEntity.stream().map(cashBoxEntityMapper::toCashBox).collect(Collectors.toList());
    }

    @Override
    public CashBox findUnique(String customerId, String nameBox) {
        var cashBoxEntity = cashBoxRepository.findByCustomerIdAndNameBox(customerId, nameBox);
        return cashBoxEntityMapper.toCashBox(cashBoxEntity);
    }
}
