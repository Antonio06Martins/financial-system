package com.antonio.pawn.entrypoint.controller.mapper;

import com.antonio.pawn.core.domain.PawnAvailableDomain;
import com.antonio.pawn.entrypoint.controller.response.PawnAvailableResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PawnMapper {

    PawnAvailableResponse toPawnAvailableResponse(PawnAvailableDomain pawnAvailableDomain);

}
