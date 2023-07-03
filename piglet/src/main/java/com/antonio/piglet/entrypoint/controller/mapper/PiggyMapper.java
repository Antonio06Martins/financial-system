package com.antonio.piglet.entrypoint.controller.mapper;

import com.antonio.piglet.core.domain.Piggy;
import com.antonio.piglet.entrypoint.controller.request.PiggyRequest;
import com.antonio.piglet.entrypoint.controller.response.PiggyResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PiggyMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customerId", source = "customerId")
    Piggy toPiggy(PiggyRequest piggyRequest, String customerId);

    PiggyResponse toPiggyResponse(Piggy piggy);
}
