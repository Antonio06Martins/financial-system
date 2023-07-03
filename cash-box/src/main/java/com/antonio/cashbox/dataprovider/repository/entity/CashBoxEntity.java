package com.antonio.cashbox.dataprovider.repository.entity;

import com.antonio.cashbox.core.enumeration.StatusBox;
import com.antonio.cashbox.core.enumeration.TypeBox;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@Document(collection = "cash-box")
public class CashBoxEntity {

    @Id
    private String id;
    private String customerId;
    private String nameBox;
    private BigDecimal amountBlocked;
    private BigDecimal accountBalance;
    private TypeBox typeBox;
    private StatusBox statusBox;

}
