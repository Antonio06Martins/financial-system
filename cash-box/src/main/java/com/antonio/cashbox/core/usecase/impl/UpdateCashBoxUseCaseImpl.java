package com.antonio.cashbox.core.usecase.impl;

import com.antonio.cashbox.core.dataprovider.FindCashBoxBy;
import com.antonio.cashbox.core.dataprovider.UpdateCashBox;
import com.antonio.cashbox.core.domain.CashBox;
import com.antonio.cashbox.core.enumeration.StatusBox;
import com.antonio.cashbox.core.usecase.UpdateCashBoxUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class UpdateCashBoxUseCaseImpl implements UpdateCashBoxUseCase {

    private final FindCashBoxBy findCashBoxBy;

    private final UpdateCashBox updateCashBox;

    @Override
    public void update(String customerId, String nameBox, BigDecimal amount, BigDecimal amountBlocked) {

        var cashBoxBase = findCashBoxBy.findUnique(customerId, nameBox);
        var cashBoxCheck = cashBoxBase.getStatusBox().equals(StatusBox.BLOCKED);
        var amountBlockedCheck = Optional.ofNullable(amountBlocked);

        if(!cashBoxCheck && Objects.equals(amountBlocked, new BigDecimal(BigInteger.ZERO))) {
            var newUpdateCashBox = CashBox.builder()
                    .id(cashBoxBase.getId())
                    .customerId(customerId)
                    .nameBox(nameBox)
                    .accountBalance(getAccountBalance(amount, cashBoxBase))
                    .amountBlocked(cashBoxBase.getAmountBlocked())
                    .typeBox(cashBoxBase.getTypeBox())
                    .statusBox(cashBoxBase.getStatusBox()) //tem que fazer aqui para considerar colocando saldo e mudando o status para parcial
                    .build();
            updateCashBox.update(newUpdateCashBox);
        } else if(amountBlockedCheck.isPresent()) {
            var newUpdateCashBox = CashBox.builder()
                    .id(cashBoxBase.getId())
                    .customerId(customerId)
                    .nameBox(cashBoxBase.getNameBox())
                    .accountBalance(cashBoxBase.getAccountBalance())
                    .amountBlocked(getBlocked(amountBlocked, cashBoxBase))
                    .typeBox(cashBoxBase.getTypeBox())
                    .statusBox(getStatusBox(cashBoxBase, amountBlocked))
                    .build();
            updateCashBox.update(newUpdateCashBox);
        } else {
            throw new IllegalStateException("Não corresponde ao Cash Box selecionado");
        }
    }

    private static StatusBox getStatusBox(CashBox cashBoxBase, BigDecimal amountBlocked) {
        StatusBox statusBox = null;

        var saldoAgoraDeBloqueio = getBlocked(amountBlocked, cashBoxBase);

        if(saldoAgoraDeBloqueio.compareTo(cashBoxBase.getAccountBalance()) == 0) {
            statusBox = StatusBox.BLOCKED;

        } else if(cashBoxBase.getAccountBalance().compareTo(saldoAgoraDeBloqueio) == 1 && saldoAgoraDeBloqueio.compareTo(BigDecimal.ZERO) == 1) {
            statusBox = StatusBox.PARTIALLY_BLOCKED;

        } else if(cashBoxBase.getAccountBalance().compareTo(saldoAgoraDeBloqueio) == 1) {
            statusBox = StatusBox.UNLOCKED;
        } else if(saldoAgoraDeBloqueio.compareTo(cashBoxBase.getAccountBalance()) == 1) {
            throw new IllegalArgumentException("Invalid");
        }

        return statusBox;
    }

    private static BigDecimal getBlocked(BigDecimal amountBlocked, CashBox cashBoxBase) {
        var negativo = amountBlocked.negate();
        BigDecimal result = null;
        if(negativo.equals(amountBlocked)) {
            result = cashBoxBase.getAmountBlocked().subtract(amountBlocked);
        } else {
            result = cashBoxBase.getAmountBlocked().add(amountBlocked);
        }
        return result;
    }

    private static BigDecimal getAccountBalance(BigDecimal amount, CashBox cashBoxBase) {
        var negativo = amount.negate();
        BigDecimal result = null;
        if(negativo.equals(amount)) {
            result = cashBoxBase.getAccountBalance().subtract(amount);
        } else {
            result = cashBoxBase.getAccountBalance().add(amount);
        }
        return result;
    }
}
