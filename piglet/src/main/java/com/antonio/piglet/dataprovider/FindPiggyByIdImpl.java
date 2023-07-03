package com.antonio.piglet.dataprovider;

import com.antonio.piglet.core.dataprovider.FindPiggyById;
import com.antonio.piglet.core.domain.Piggy;
import com.antonio.piglet.dataprovider.repository.PiggyRepository;
import com.antonio.piglet.dataprovider.repository.mapper.PiggyEntityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class FindPiggyByIdImpl implements FindPiggyById {

    private final PiggyRepository piggyRepository;

    private final PiggyEntityMapper piggyEntityMapper;

    @Override
    public List<Piggy> find(String customerId) {
        var piggyEntity = piggyRepository.findByCustomerId(customerId);
        return piggyEntity.stream().map(piggyEntityMapper::toPiggy).collect(Collectors.toList());
    }
}
