package com.antonio.pawn.dataprovider.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record PiggyResponse(
        String namePiggy,
        String customerId,
        String placeholder) {
}
