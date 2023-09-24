package com.antonio.cashbox.entrypoint.worker.config;

import com.antonio.cashbox.core.usecase.InsertCashBoxUseCase;
import com.antonio.cashbox.entrypoint.worker.dto.CashBoxRequestDTO;
import com.antonio.cashbox.entrypoint.worker.event.CashBoxSerde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Configuration
public class CashBoxStream {

    private static final Logger LOGGER = LoggerFactory.getLogger(CashBoxStream.class);
    private final String cashBoxTopic;
    private final InsertCashBoxUseCase insertCashBoxUseCase;

    CashBoxStream(@Value("${spring.kafka.topic}") String cashBoxTopic,
                  InsertCashBoxUseCase insertCashBoxUseCase) {
        this.cashBoxTopic = cashBoxTopic;
        this.insertCashBoxUseCase = insertCashBoxUseCase;

    }

    private void insert(String s, CashBoxRequestDTO cashBoxRequestDTO) {
        LOGGER.info("salvando entidade: {} ", s);
        insertCashBoxUseCase.insert(cashBoxRequestDTO.customerId(), cashBoxRequestDTO.nameBox(), cashBoxRequestDTO.typeBox());
    }

    @Bean
    KStream<String, CashBoxRequestDTO> cashBoxKStream(StreamsBuilder streamsBuilder) {

        return streamsBuilder.stream(
                cashBoxTopic,
                Consumed.with(Serdes.String(), new CashBoxSerde())
        ).map(
                this::checkId
        ).filter(
                this::filterIdNotNull
        ).peek(
                (k,v) -> LOGGER.info("Consumindo tópico: {}, {}", k, v)
        ).peek(
                this::insert
        );

    }

    private Boolean filterIdNotNull(String key, CashBoxRequestDTO value) {
        if (key == null) {
            LOGGER.info("id null: {}", value.customerId());
        }
        return key != null;
    }

    private KeyValue<String, CashBoxRequestDTO> checkId(String key, CashBoxRequestDTO value) {
        return new KeyValue<>(
                Optional.ofNullable(value.customerId())
                        .map(String::valueOf)
                        .orElse(key),
                value
        );
    }


}
