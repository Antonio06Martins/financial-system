package com.antonio.piglet.entrypoint.controller.request;

import com.antonio.piglet.core.enumeration.Placeholder;
import lombok.Builder;

@Builder
public record PiggyRequest(
        String namePiggy,
        Placeholder placeholder) {
}
