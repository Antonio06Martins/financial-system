package com.antonio.cashbox.dataprovider;

import com.antonio.cashbox.core.dataprovider.FindCashBoxBy;
import com.antonio.cashbox.core.domain.CashBoxDomain;
import com.antonio.cashbox.core.enumeration.TypeBox;
import com.antonio.cashbox.dataprovider.repository.CashBoxRepository;
import com.antonio.cashbox.dataprovider.repository.entity.CashBoxEntity;
import com.antonio.cashbox.dataprovider.repository.mapper.CashBoxEntityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class FindCashBoxByImpl implements FindCashBoxBy {

    private final CashBoxRepository cashBoxRepository;

    private final CashBoxEntityMapper cashBoxEntityMapper;

    @Override
    public List<CashBoxDomain> findAll(String customerId, TypeBox typeBox) {
        List<CashBoxEntity> cashBoxEntity = null;
        if(typeBox == null) {
            cashBoxEntity = cashBoxRepository.findByCustomerId(customerId);
        } else {
            cashBoxEntity = cashBoxRepository.findByCustomerIdAndTypeBox(customerId, typeBox);
        }

       var ver =  cashBoxEntity;

        return cashBoxEntityMapper.toCashBoxList(cashBoxEntity);
    }

    @Override
    public CashBoxDomain findUnique(String customerId, String nameBox) {
        var cashBoxEntity = cashBoxRepository.findByCustomerIdAndNameBox(customerId, nameBox);
        return cashBoxEntityMapper.toCashBox(cashBoxEntity);
    }
}
