package com.antonio.pawn.entrypoint.controller.response;

import com.antonio.pawn.core.enumeration.TypePawn;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record PawnAvailableResponse(
        BigDecimal totalAvailableBalance,
        List<PawnResponse> pawns) {

    @Builder
    public record PawnResponse(
            String name,
            String nameBox,
            BigDecimal balance,
            TypePawn typePawn) {

    }

}
