package com.antonio.piglet.core.usecase;

import com.antonio.piglet.core.domain.Piggy;
import com.antonio.piglet.core.enumeration.Placeholder;

public interface UpdatePiggyUseCase {

    void update(String customerId, Placeholder placeholder, Piggy piggy);

}
