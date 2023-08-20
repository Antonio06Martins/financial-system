package com.antonio.cashbox.core.usecase;

import java.math.BigDecimal;

public interface UpdateCashBoxUseCase {

    void updateAmount(String customerId, String nameBox, BigDecimal amount);
    void updateAmountBlocked(String customerId, String nameBox, BigDecimal amountBlocked);

}
