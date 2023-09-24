package com.antonio.piglet.entrypoint.worker.dto;

import lombok.Builder;

@Builder
public record PiggyHasLockDTO(
        String customerId,
        String placeholder,
        Boolean hasLock) {
}
//{
//  "customerId": "30241108",
//  "placeholder": "PIGGY_1",
//  "hasLock": "true"
//}