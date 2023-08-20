package com.antonio.cashbox.core.usecase;

import com.antonio.cashbox.core.domain.CashBoxDomain;
import com.antonio.cashbox.core.enumeration.TypeBox;

import java.util.List;

public interface FindCashBoxUseCase {

    List<CashBoxDomain> find(final String customerId, TypeBox typeBox);

}
