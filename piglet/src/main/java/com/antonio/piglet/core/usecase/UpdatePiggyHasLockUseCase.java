package com.antonio.piglet.core.usecase;

import com.antonio.piglet.core.domain.Piggy;
import com.antonio.piglet.core.enumeration.Placeholder;

public interface UpdatePiggyHasLockUseCase {

    void updateHasLock(String customerId, Placeholder placeholder, Boolean hasLock);

}
