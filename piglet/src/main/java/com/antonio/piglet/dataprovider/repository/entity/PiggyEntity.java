package com.antonio.piglet.dataprovider.repository.entity;

import com.antonio.piglet.core.enumeration.Placeholder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "piggy")
public class PiggyEntity {

    @Id
    private String id;
    private String namePiggy;
    private String customerId;
    private Placeholder placeholder;

}
