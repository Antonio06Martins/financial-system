package com.antonio.cashbox.entrypoint.worker.event;

import com.antonio.cashbox.entrypoint.worker.dto.CashBoxRequestDTO;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

public class CashBoxSerde implements Serde<CashBoxRequestDTO> {

    private final JsonDeserializer<CashBoxRequestDTO> deserializer;

    public CashBoxSerde() {
        this.deserializer = new JsonDeserializer<>(CashBoxRequestDTO.class);
    }

    @Override
    public Serializer<CashBoxRequestDTO> serializer() {
        return null;
    }

    @Override
    public Deserializer<CashBoxRequestDTO> deserializer() {
        return deserializer;
    }
}
