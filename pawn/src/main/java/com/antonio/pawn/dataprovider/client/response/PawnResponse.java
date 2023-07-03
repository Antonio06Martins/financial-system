package com.antonio.pawn.dataprovider.client.response;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record PawnResponse(
        String namePiggy,
        String typeBox,
        String statusBox,
        BigDecimal accountBalance) {
}
