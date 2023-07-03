package com.antonio.piglet.dataprovider.repository.mapper;

import com.antonio.piglet.core.domain.Piggy;
import com.antonio.piglet.dataprovider.repository.entity.PiggyEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PiggyEntityMapper {

    PiggyEntity toPiggyEntity(Piggy piggy);

    Piggy toPiggy(PiggyEntity piggyEntity);

}
