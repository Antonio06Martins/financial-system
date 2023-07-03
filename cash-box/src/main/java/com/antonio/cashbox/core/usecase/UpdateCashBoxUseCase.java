package com.antonio.cashbox.core.usecase;

import java.math.BigDecimal;

public interface UpdateCashBoxUseCase {

    void update(String customerId, String nameBox, BigDecimal amount, BigDecimal amountBlocked);

}
