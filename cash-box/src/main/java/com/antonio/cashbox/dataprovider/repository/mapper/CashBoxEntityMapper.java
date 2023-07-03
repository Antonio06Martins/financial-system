package com.antonio.cashbox.dataprovider.repository.mapper;

import com.antonio.cashbox.core.domain.CashBox;
import com.antonio.cashbox.dataprovider.repository.entity.CashBoxEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CashBoxEntityMapper {

    CashBoxEntity toCashBoxEntity(CashBox cashBox);

    CashBox toCashBox(CashBoxEntity cashBoxEntity);

}
