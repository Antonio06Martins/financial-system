package com.antonio.pawn.core.dataprovider;

import com.antonio.pawn.core.domain.PawnAvailableDomain;

public interface GetPawnGateway {

    PawnAvailableDomain getPawnAvailable(final String customerId);

}
