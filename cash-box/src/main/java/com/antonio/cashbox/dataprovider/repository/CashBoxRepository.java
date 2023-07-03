package com.antonio.cashbox.dataprovider.repository;

import com.antonio.cashbox.core.domain.CashBox;
import com.antonio.cashbox.core.enumeration.StatusBox;
import com.antonio.cashbox.core.enumeration.TypeBox;
import com.antonio.cashbox.dataprovider.repository.entity.CashBoxEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CashBoxRepository extends MongoRepository<CashBoxEntity, String> {

    List<CashBoxEntity> findByCustomerIdAndTypeBoxAndStatusBox(String customerId, TypeBox typeBox, StatusBox statusBox);

    CashBoxEntity findByCustomerIdAndNameBox(String customerId, String nameBox);

    List<CashBoxEntity> findByCustomerIdAndTypeBox(String customerId, TypeBox typeBox);

}
