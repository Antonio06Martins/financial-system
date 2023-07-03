package com.antonio.pawn.entrypoint.controller;

import com.antonio.pawn.dataprovider.client.response.PawnBlockedResponse;
import com.antonio.pawn.dataprovider.client.response.PawnUnlockedResponse;
import com.antonio.pawn.dataprovider.client.response.PiggyResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple3;

import java.util.List;

@Tag(name = "Pawn")
@RequestMapping("/v1/Pawn")
public interface PawnController {

    @Operation(summary = "Pawn")
    @Parameter(name = "customer-id", in = ParameterIn.HEADER, required = true, description = "customerId", example = "30241106")
    @Parameter(name = "typeBox", in = ParameterIn.HEADER, required = true, description = "typeBox", example = "PIGGY")
    @Parameter(name = "statusBox", in = ParameterIn.HEADER, required = true, description = "statusBox", example = "UNLOCKED")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Buscado com sucesso")
    })
    @GetMapping("/customerId")
    @ResponseStatus(HttpStatus.OK)
    Flux<Tuple3<List<PiggyResponse>, List<PawnUnlockedResponse>, List<PawnBlockedResponse>>> findByCustomerId(
            @RequestHeader("customer-id") final String customerId,
            @RequestHeader("typeBox") final String typeBox,
            @RequestHeader("statusBox") final String statusBox

    );
}
