package com.antonio.cashbox.core.domain;

import com.antonio.cashbox.core.enumeration.StatusBox;
import com.antonio.cashbox.core.enumeration.TypeBox;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CashBox {

    private String id;
    private String customerId;
    private String nameBox;
    private BigDecimal amountBlocked;
    private BigDecimal accountBalance;
    private TypeBox typeBox;
    private StatusBox statusBox;

}
