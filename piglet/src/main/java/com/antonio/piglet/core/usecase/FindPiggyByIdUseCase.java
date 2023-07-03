package com.antonio.piglet.core.usecase;

import com.antonio.piglet.core.domain.Piggy;

import java.util.List;

public interface FindPiggyByIdUseCase {

    List<Piggy> find(final String customerId);

}
