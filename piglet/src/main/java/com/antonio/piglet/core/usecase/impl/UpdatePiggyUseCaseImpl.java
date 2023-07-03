package com.antonio.piglet.core.usecase.impl;

import com.antonio.piglet.core.dataprovider.FindPiggyById;
import com.antonio.piglet.core.dataprovider.UpdatePiggy;
import com.antonio.piglet.core.domain.Piggy;
import com.antonio.piglet.core.enumeration.Placeholder;
import com.antonio.piglet.core.usecase.UpdatePiggyUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UpdatePiggyUseCaseImpl implements UpdatePiggyUseCase {

    private final FindPiggyById findPiggyById;

    private final UpdatePiggy updatePiggy;

    @Override
    public void update(String customerId, Placeholder placeholder, Piggy piggy) {
        var piggyBase = findPiggyById.find(customerId);
        var piggyCheck = piggyBase.stream().filter(p -> p.getPlaceholder().equals(placeholder)).findFirst();
        if(piggyCheck.isPresent()) {
            var newUpdatePiggy = Piggy.builder()
                    .id(piggyCheck.get().getId())
                    .customerId(customerId)
                    .namePiggy(piggy.getNamePiggy())
                    .placeholder(placeholder)
                    .build();
            updatePiggy.update(newUpdatePiggy);
        } else {
            throw new IllegalStateException("Não corresponde ao Piggy selecionado");
        }

    }

}
