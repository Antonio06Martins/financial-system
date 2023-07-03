package com.antonio.piglet.core.usecase.impl;

import com.antonio.piglet.core.dataprovider.FindPiggyById;
import com.antonio.piglet.core.domain.Piggy;
import com.antonio.piglet.core.usecase.FindPiggyByIdUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class FindPiggyByIdUseCaseImpl implements FindPiggyByIdUseCase {


    private final FindPiggyById findPiggyById;

    @Override
    public List<Piggy> find(String customerId) {
        return Optional.ofNullable(findPiggyById.find(customerId))
                .orElseThrow(() -> new RuntimeException("Piggy not found"));

    }

}