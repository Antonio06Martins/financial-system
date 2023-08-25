package com.antonio.pawn.dataprovider.client.response;

import lombok.Builder;

@Builder
public record PiggyResponse(
        String namePiggy,
        String customerId,
        String placeholder) {
}
