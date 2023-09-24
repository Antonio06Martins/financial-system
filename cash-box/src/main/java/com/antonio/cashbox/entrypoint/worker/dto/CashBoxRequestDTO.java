package com.antonio.cashbox.entrypoint.worker.dto;

import com.antonio.cashbox.core.enumeration.TypeBox;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Builder
@Jacksonized
public record CashBoxRequestDTO(
        String customerId,
        String nameBox,
        TypeBox typeBox) {
}