package com.antonio.piglet.entrypoint.controller.response;

import com.antonio.piglet.core.enumeration.Placeholder;

public record PiggyResponse(
        String namePiggy,
        String customerId,
        Placeholder placeholder) {
}
