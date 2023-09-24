package com.antonio.piglet.entrypoint.controller.impl;

import com.antonio.piglet.core.enumeration.Placeholder;
import com.antonio.piglet.core.usecase.FindPiggyByIdUseCase;
import com.antonio.piglet.core.usecase.InsertPiggyUseCase;
import com.antonio.piglet.core.usecase.UpdatePiggyUseCase;
import com.antonio.piglet.entrypoint.controller.PiggyController;
import com.antonio.piglet.entrypoint.controller.mapper.PiggyMapper;
import com.antonio.piglet.entrypoint.controller.request.PiggyRequest;
import com.antonio.piglet.entrypoint.controller.response.PiggyResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
public class PiggyControllerImpl implements PiggyController {

    private final InsertPiggyUseCase insertPiggyUseCase;
    private final FindPiggyByIdUseCase findPiggyByIdUseCase;
    private final UpdatePiggyUseCase updatePiggyUseCase;
    private final PiggyMapper piggyMapper;

    @Override
    public ResponseEntity<Void> insert(String customerId, PiggyRequest piggyRequest) {
        var piggy = piggyMapper.toPiggy(piggyRequest, customerId);
        insertPiggyUseCase.insert(piggy);
        return ResponseEntity.ok().build();
    }

    @Override
    public List<PiggyResponse> findByCustomerId(String customerId) {
        var piggy = findPiggyByIdUseCase.find(customerId);
        return piggy.stream().map(piggyMapper::toPiggyResponse).collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<Void> update(String customerId, Placeholder placeholder, PiggyRequest piggyRequest) {
        var piggy = piggyMapper.toPiggy(piggyRequest, customerId);
        updatePiggyUseCase.update(customerId, placeholder, piggy);
        return ResponseEntity.ok().build();
    }


}
