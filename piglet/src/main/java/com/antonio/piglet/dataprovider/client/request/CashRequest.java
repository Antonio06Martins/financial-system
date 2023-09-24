package com.antonio.piglet.dataprovider.client.request;

import com.antonio.piglet.core.enumeration.TypeBox;
import lombok.Builder;

@Builder
public record CashRequest(
        String nameBox,
        TypeBox typeBox) {
}
