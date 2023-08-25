package com.antonio.pawn.core.domain;

import com.antonio.pawn.core.enumeration.TypePawn;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record PawnAvailableDomain(
        BigDecimal totalAvailableBalance,
        List<PawnDomain> pawns) {

    @Builder
    public record PawnDomain(
            String name,
            String nameBox,
            BigDecimal balance,
            TypePawn typePawn) {

    }
}
