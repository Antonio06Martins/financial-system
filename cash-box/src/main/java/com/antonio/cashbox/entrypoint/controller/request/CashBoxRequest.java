package com.antonio.cashbox.entrypoint.controller.request;

import com.antonio.cashbox.core.enumeration.TypeBox;
import lombok.Builder;

@Builder
public record CashBoxRequest(
        String nameBox,
        TypeBox typeBox) {
}
