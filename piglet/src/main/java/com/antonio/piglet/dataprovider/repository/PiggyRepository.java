package com.antonio.piglet.dataprovider.repository;

import com.antonio.piglet.dataprovider.repository.entity.PiggyEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PiggyRepository extends MongoRepository<PiggyEntity, String> {

    List<PiggyEntity> findByCustomerId(String customerId);

}
