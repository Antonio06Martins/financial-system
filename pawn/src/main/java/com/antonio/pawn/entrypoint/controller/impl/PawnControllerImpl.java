package com.antonio.pawn.entrypoint.controller.impl;

import com.antonio.pawn.core.usecase.GetPawnUseCase;
import com.antonio.pawn.entrypoint.controller.PawnController;
import com.antonio.pawn.entrypoint.controller.mapper.PawnMapper;
import com.antonio.pawn.entrypoint.controller.response.PawnAvailableResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class PawnControllerImpl implements PawnController {

    private final GetPawnUseCase getPawnUseCase;
    private final PawnMapper pawnMapper;

    @Override
    public PawnAvailableResponse findByCustomerId(String customerId) {

        final var response = getPawnUseCase.getPawnAvailable(customerId);

        return pawnMapper.toPawnAvailableResponse(response);
    }
}
