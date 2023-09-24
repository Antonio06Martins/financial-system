package com.antonio.piglet.entrypoint.worker.event;

import com.antonio.piglet.entrypoint.worker.dto.PiggyHasLockDTO;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

public class PiggyHasLockSerde implements Serde<PiggyHasLockDTO> {

    private JsonDeserializer<PiggyHasLockDTO> deserializer;

    public PiggyHasLockSerde() {
        this.deserializer = new JsonDeserializer<>(PiggyHasLockDTO.class);
    }

    public Serializer<PiggyHasLockDTO> serializer() {
        return null;
    }

    @Override
    public Deserializer<PiggyHasLockDTO> deserializer() {
        return deserializer;
    }

}

