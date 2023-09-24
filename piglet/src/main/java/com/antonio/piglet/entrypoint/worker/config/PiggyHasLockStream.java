package com.antonio.piglet.entrypoint.worker.config;

import com.antonio.piglet.core.enumeration.Placeholder;
import com.antonio.piglet.core.usecase.UpdatePiggyHasLockUseCase;
import com.antonio.piglet.entrypoint.worker.dto.PiggyHasLockDTO;
import com.antonio.piglet.entrypoint.worker.event.PiggyHasLockSerde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

import static org.slf4j.LoggerFactory.*;

@Configuration
class PiggyHasLockStream {

    private static final Logger LOGGER = getLogger(PiggyHasLockStream.class);
    private final String hasLock;
    private final UpdatePiggyHasLockUseCase updatePiggy;

    PiggyHasLockStream(@Value("${spring.kafka.haslock}") String hasLock,
                       UpdatePiggyHasLockUseCase updatePiggy) {

        this.hasLock = hasLock;
        this.updatePiggy = updatePiggy;
    }

    private void updatePiggyHasLock(String s, PiggyHasLockDTO piggyHasLockDTO) {
        updatePiggy.updateHasLock(piggyHasLockDTO.customerId(),
                Placeholder.valueOf(piggyHasLockDTO.placeholder()), piggyHasLockDTO.hasLock());
        LOGGER.info("salvando entidade: {} ", s);
    }

    @Bean
    KStream<String, PiggyHasLockDTO> piggyHasLockKStream(StreamsBuilder streamsBuilder) {

        return streamsBuilder.stream(
                hasLock,
                Consumed.with(Serdes.String(), new PiggyHasLockSerde())
        ).map(
                this::checkId
        ).filter(
                this::filterIdNotNull
        ).peek(
                (k,v) -> LOGGER.info("Consumindo tópico: {}, {}", k, v)
        ).peek(
                this::updatePiggyHasLock
        );

      // aqui onde vou receber de contract quando tiver o contrato no fim da saga

    }


    private Boolean filterIdNotNull(String key, PiggyHasLockDTO value) {
        if (key == null) {
            LOGGER.info("id null: {}", value.customerId());
        }
        return key != null;
    }

    private KeyValue<String, PiggyHasLockDTO> checkId(String key, PiggyHasLockDTO value) {
        return new KeyValue<>(
                Optional.ofNullable(value.customerId())
                        .map(String::valueOf)
                        .orElse(key),
                value
        );
    }


}

