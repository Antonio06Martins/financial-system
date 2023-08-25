package com.antonio.pawn.entrypoint.controller;

import com.antonio.pawn.entrypoint.controller.response.PawnAvailableResponse;
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

@Tag(name = "Pawn")
@RequestMapping("/v1")
public interface PawnController {

    @Operation(summary = "Pawn")
    @Parameter(name = "customer-id", in = ParameterIn.HEADER, required = true, description = "customerId", example = "30241106")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Buscado com sucesso")
    })

    @GetMapping("/pawn")
    @ResponseStatus(HttpStatus.OK)
    PawnAvailableResponse findByCustomerId(
            @RequestHeader("customer-id") final String customerId);
}
