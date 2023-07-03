package com.antonio.cashbox.core.dataprovider;

import com.antonio.cashbox.core.domain.CashBox;
import com.antonio.cashbox.core.enumeration.StatusBox;
import com.antonio.cashbox.core.enumeration.TypeBox;

import java.util.List;

public interface FindCashBoxBy {

    List<CashBox> findAll(String customerId, TypeBox typeBox, StatusBox statusBox);

    CashBox findUnique(String customerId, String nameBox);
}
