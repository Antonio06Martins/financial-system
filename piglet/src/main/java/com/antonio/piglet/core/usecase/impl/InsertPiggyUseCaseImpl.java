package com.antonio.piglet.core.usecase.impl;

import com.antonio.piglet.core.dataprovider.InsertPiggy;
import com.antonio.piglet.core.domain.Piggy;
import com.antonio.piglet.core.usecase.InsertPiggyUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class InsertPiggyUseCaseImpl implements InsertPiggyUseCase {

    private final InsertPiggy insertPiggy;

    @Override
    public void insert(Piggy piggy) {

        insertPiggy.insert(piggy);
    }
}
