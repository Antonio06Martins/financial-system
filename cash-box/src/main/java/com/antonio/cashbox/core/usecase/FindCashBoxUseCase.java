package com.antonio.cashbox.core.usecase;

import com.antonio.cashbox.core.domain.CashBox;
import com.antonio.cashbox.core.enumeration.StatusBox;
import com.antonio.cashbox.core.enumeration.TypeBox;

import java.util.List;

public interface FindCashBoxUseCase {

    List<CashBox> find(final String customerId, TypeBox typeBox, StatusBox statusBox);

}
