package com.antonio.cashbox.core.dataprovider;

import com.antonio.cashbox.core.domain.CashBoxDomain;
import com.antonio.cashbox.core.enumeration.TypeBox;

import java.util.List;

public interface FindCashBoxBy {

    List<CashBoxDomain> findAll(String customerId, TypeBox typeBox);

    CashBoxDomain findUnique(String customerId, String nameBox);
}
