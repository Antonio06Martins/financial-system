package com.antonio.pawn.core.usecase.impl;

import com.antonio.pawn.core.dataprovider.GetPawnGateway;
import com.antonio.pawn.core.domain.PawnAvailableDomain;
import com.antonio.pawn.core.usecase.GetPawnUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class GetPawnUseCaseImpl implements GetPawnUseCase {

    private final GetPawnGateway getPawnGateway;

    @Override
    public PawnAvailableDomain getPawnAvailable(String customerId) {

        return getPawnGateway.getPawnAvailable(customerId);

    }
}
