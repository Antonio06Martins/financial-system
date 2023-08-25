package com.antonio.pawn.core.usecase;

import com.antonio.pawn.core.domain.PawnAvailableDomain;

public interface GetPawnUseCase {

    PawnAvailableDomain getPawnAvailable(final String customerId);

}
