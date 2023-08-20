package com.antonio.cashbox.dataprovider.repository.mapper;

import com.antonio.cashbox.core.domain.CashBoxDomain;
import com.antonio.cashbox.dataprovider.repository.entity.CashBoxEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CashBoxEntityMapper {

    CashBoxEntity toCashBoxEntity(CashBoxDomain cashBoxDomain);

//    @Mapping(target = "selectBlockedBalance", source = "cashBoxEntity.selectBlockedBalance")
//    @Mapping(target = "selectBalance", source = "cashBoxEntity.selectBalance")
    CashBoxDomain toCashBox(CashBoxEntity cashBoxEntity);

//    @Mapping(target = "selectBlockedBalance", source = "cashBoxEntity.selectBlockedBalance")
//    @Mapping(target = "selectBalance", source = "cashBoxEntity.selectBalance")
    List<CashBoxDomain> toCashBoxList(List<CashBoxEntity> cashBoxEntity);

}
