package com.antonio.piglet.core.dataprovider;

import com.antonio.piglet.core.domain.Piggy;

import java.util.List;

public interface FindPiggyById {

    List<Piggy> find(final String customerId);

}
