package com.antonio.pawn.entrypoint.controller.impl;

import com.antonio.pawn.dataprovider.client.PawnClient;
import com.antonio.pawn.dataprovider.client.response.PawnBlockedResponse;
import com.antonio.pawn.dataprovider.client.response.PawnUnlockedResponse;
import com.antonio.pawn.dataprovider.client.response.PiggyResponse;
import com.antonio.pawn.entrypoint.controller.PawnController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple3;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class PawnControllerImpl implements PawnController {

    private final PawnClient pawnClient;

    @Override
    public Flux<Tuple3<List<PiggyResponse>, List<PawnUnlockedResponse>, List<PawnBlockedResponse>>> findByCustomerId(String customerId, String typeBox, String statusBox) {


        return pawnClient.find(customerId);

    }
}
