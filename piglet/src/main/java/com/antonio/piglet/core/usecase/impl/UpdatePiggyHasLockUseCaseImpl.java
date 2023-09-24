package com.antonio.piglet.core.usecase.impl;

import com.antonio.piglet.core.dataprovider.FindPiggyById;
import com.antonio.piglet.core.dataprovider.UpdatePiggy;
import com.antonio.piglet.core.domain.Piggy;
import com.antonio.piglet.core.enumeration.Placeholder;
import com.antonio.piglet.core.usecase.UpdatePiggyHasLockUseCase;
import com.antonio.piglet.core.usecase.UpdatePiggyUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UpdatePiggyHasLockUseCaseImpl implements UpdatePiggyHasLockUseCase {

    private final FindPiggyById findPiggyById;

    private final UpdatePiggy updatePiggy;

    @Override
    public void updateHasLock(String customerId, Placeholder placeholder, Boolean hasLockk) {
        var piggyBase = findPiggyById.find(customerId);
        var piggyCheck = piggyBase.stream().filter(p -> p.getPlaceholder().equals(placeholder)).findFirst();
        if(piggyCheck.isPresent()) {
            var newUpdatePiggy = Piggy.builder()
                    .id(piggyCheck.get().getId())
                    .customerId(customerId)
                    .namePiggy(piggyCheck.get().getNamePiggy())
                    .placeholder(placeholder)
                    .hasLock(hasLockk)
                    .build();
            updatePiggy.update(newUpdatePiggy);
        } else {
            throw new IllegalStateException("Não corresponde ao Piggy selecionado");
        }

    }

}
