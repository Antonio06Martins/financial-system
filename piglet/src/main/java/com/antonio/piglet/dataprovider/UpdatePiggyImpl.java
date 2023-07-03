package com.antonio.piglet.dataprovider;

import com.antonio.piglet.core.dataprovider.UpdatePiggy;
import com.antonio.piglet.core.domain.Piggy;
import com.antonio.piglet.dataprovider.repository.PiggyRepository;
import com.antonio.piglet.dataprovider.repository.mapper.PiggyEntityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UpdatePiggyImpl implements UpdatePiggy {

    private final PiggyRepository piggyRepository;

    private final PiggyEntityMapper piggyEntityMapper;

    @Override
    public void update(Piggy piggy) {
        var piggyEntity = piggyEntityMapper.toPiggyEntity(piggy);
        piggyRepository.save(piggyEntity);
    }
}
