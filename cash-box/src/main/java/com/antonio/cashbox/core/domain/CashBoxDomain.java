package com.antonio.cashbox.core.domain;

import com.antonio.cashbox.core.enumeration.TypeBox;
import lombok.*;

import java.time.LocalDateTime;

//@Getter
//@Setter
@Builder
@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class CashBoxDomain {

    private String id;
    private String customerId;
    private String nameBox;
    private TypeBox typeBox;
    private LocalDateTime createdIn;
    private SelectBlockedBalanceDomain selectBlockedBalance;
    private SelectBalanceDomain selectBalance;

}
