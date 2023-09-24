package com.antonio.piglet.dataprovider;

import com.antonio.piglet.core.dataprovider.InsertPiggy;
import com.antonio.piglet.core.domain.Piggy;
import com.antonio.piglet.core.enumeration.TypeBox;
import com.antonio.piglet.dataprovider.client.CashBoxClient;
import com.antonio.piglet.dataprovider.client.request.CashRequest;
import com.antonio.piglet.dataprovider.repository.PiggyRepository;
import com.antonio.piglet.dataprovider.repository.mapper.PiggyEntityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class InsertPiggyImpl implements InsertPiggy {

    private final PiggyRepository piggyRepository;

    private final PiggyEntityMapper piggyEntityMapper;

    private final CashBoxClient cashBoxClient;

    @Override
    public void insert(Piggy piggy) {
        cashBoxClient.createCashBox(piggy.getCustomerId(), CashRequest.builder()
                .nameBox(piggy.getPlaceholder().name())
                .typeBox(TypeBox.PIGGY)
                .build());

        var piggyEntity = piggyEntityMapper.toPiggyEntity(piggy);
        piggyRepository.save(piggyEntity);
    }
}
