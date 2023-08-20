package com.antonio.cashbox.core.usecase;

import com.antonio.cashbox.core.domain.CashBoxDomain;
import com.antonio.cashbox.core.enumeration.TypeBox;

public interface InsertCashBoxUseCase {

    void insert(String customerId, String nameBox, TypeBox typeBox);

}
