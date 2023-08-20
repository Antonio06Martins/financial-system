package com.antonio.cashbox.core.usecase.impl;

import com.antonio.cashbox.core.dataprovider.FindCashBoxBy;
import com.antonio.cashbox.core.dataprovider.UpdateCashBox;
import com.antonio.cashbox.core.domain.CashBoxDomain;
import com.antonio.cashbox.core.domain.SelectBalanceDomain;
import com.antonio.cashbox.core.domain.SelectBlockedBalanceDomain;
import com.antonio.cashbox.core.usecase.UpdateCashBoxUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class UpdateCashBoxUseCaseImpl implements UpdateCashBoxUseCase {

    private final FindCashBoxBy findCashBoxBy;

    private final UpdateCashBox updateCashBox;



    @Override
    public void updateAmount(String customerId, String nameBox, BigDecimal amount) {
        final var cashBox = Optional.ofNullable(findCashBoxBy.findUnique(customerId, nameBox))
                .orElseThrow(() -> new IllegalArgumentException("Cash Box not found"));

        final var accountBalanceInBase = getSelectBalanceDomain(cashBox);
        final var amountBlocked = getSelectBlockedBalanceDomain(cashBox);

        SelectBlockedBalanceDomain selectBlockedBalanceDomain = null;
        if(amountBlocked.amountBlocked().compareTo(BigDecimal.ZERO) == 0 ) {
            selectBlockedBalanceDomain = cashBox.getSelectBlockedBalance();
        } else if(amountBlocked.amountBlocked().compareTo(BigDecimal.ZERO) > 0) {
            selectBlockedBalanceDomain = SelectBlockedBalanceDomain.builder()
                    .amountBlocked(BigDecimal.ZERO)
                    .lastUpdate(LocalDateTime.now())
                    .build();
        } else {
            selectBlockedBalanceDomain = SelectBlockedBalanceDomain.builder()
                    .amountBlocked(amountBlocked.amountBlocked().subtract(amount))
                    .lastUpdate(LocalDateTime.now())
                    .build();
        }

        updateCashBox.update(CashBoxDomain.builder()
                        .id(cashBox.getId())
                        .customerId(customerId)
                        .nameBox(nameBox)
                        .selectBalance(SelectBalanceDomain.builder()
                                .accountBalance(accountBalanceInBase.accountBalance().add(amount))
                                .lastUpdate(LocalDateTime.now())
                                .build())
                        .selectBlockedBalance(selectBlockedBalanceDomain)
                        .typeBox(cashBox.getTypeBox())
                        .createdIn(cashBox.getCreatedIn())
                .build());
    }

    @Override
    public void updateAmountBlocked(String customerId, String nameBox, BigDecimal amountBlocked) {
        final var cashBox = Optional.ofNullable(findCashBoxBy.findUnique(customerId, nameBox))
                .orElseThrow(() -> new IllegalArgumentException("Cash Box not found"));

        final var accountBalanceInBase = getSelectBalanceDomain(cashBox);
        final var amountBlockedInBase = getSelectBlockedBalanceDomain(cashBox);

        if(accountBalanceInBase.accountBalance().add(amountBlockedInBase.amountBlocked())
                .compareTo(amountBlockedInBase.amountBlocked().add(amountBlocked)) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente para bloqueio");
        }

        SelectBalanceDomain selectBalanceDomain = null;
        if(accountBalanceInBase.accountBalance().compareTo(BigDecimal.ZERO) == 0) {
            selectBalanceDomain = cashBox.getSelectBalance();
        } else {
            selectBalanceDomain = SelectBalanceDomain.builder()
                    .accountBalance(accountBalanceInBase.accountBalance().subtract(amountBlocked))
                    .lastUpdate(LocalDateTime.now())
                    .build();
        }

        updateCashBox.update(CashBoxDomain.builder()
                .id(cashBox.getId())
                .customerId(customerId)
                .nameBox(nameBox)
                .selectBalance(selectBalanceDomain)
                .selectBlockedBalance(SelectBlockedBalanceDomain.builder()
                        .amountBlocked(amountBlockedInBase.amountBlocked().add(amountBlocked))
                        .lastUpdate(LocalDateTime.now())
                        .build())
                .typeBox(cashBox.getTypeBox())
                .createdIn(cashBox.getCreatedIn())
                .build());
    }

    private static SelectBlockedBalanceDomain getSelectBlockedBalanceDomain(CashBoxDomain cashBox) {
        var amountBlocked = cashBox.getSelectBlockedBalance();
        if(amountBlocked == null) {
            amountBlocked = SelectBlockedBalanceDomain.builder()
                    .amountBlocked(BigDecimal.ZERO)
                    .build();
        }
        return amountBlocked;
    }

    private static SelectBalanceDomain getSelectBalanceDomain(CashBoxDomain cashBox) {
        var accountBalanceInBase = cashBox.getSelectBalance();
        if (accountBalanceInBase == null) {
            accountBalanceInBase = SelectBalanceDomain.builder()
                    .accountBalance(BigDecimal.ZERO)
                    .build();
        }
        return accountBalanceInBase;
    }
}

// O método compareTo retorna um valor negativo se o primeiro número for menor que o segundo,
// um valor positivo se o primeiro número for maior que o segundo e zero se os números forem iguais.
