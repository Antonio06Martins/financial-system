package com.antonio.piglet.core.domain;

import com.antonio.piglet.core.enumeration.Placeholder;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Piggy {

    private String id;
    private String namePiggy;
    private String customerId;
    private Placeholder placeholder;
    private Boolean hasLock;

}
