package com.antonio.piglet.entrypoint.controller;

import com.antonio.piglet.core.enumeration.Placeholder;
import com.antonio.piglet.entrypoint.controller.request.PiggyRequest;
import com.antonio.piglet.entrypoint.controller.response.PiggyResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "PIGGY")
@RequestMapping("/v1/piggy")
public interface PiggyController {

    @Operation(summary = "Piggy")
    @Parameter(name = "customer-id", in = ParameterIn.HEADER, required = true, description = "customerId", example = "30241106")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Criado com sucesso")
    })

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<Void> insert(
            @RequestHeader("customer-id") final String customerId,
            @RequestBody PiggyRequest piggyRequest
    );

    @Operation(summary = "Piggy")
    @Parameter(name = "customer-id", in = ParameterIn.HEADER, required = true, description = "customerId", example = "30241106")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Criado com sucesso")
    })
    @GetMapping("/customerId")
    @ResponseStatus(HttpStatus.OK)
    List<PiggyResponse> findByCustomerId(
            @RequestHeader("customer-id") final String customerId
    );

    @Operation(summary = "Piggy")
    @Parameter(name = "customer-id", in = ParameterIn.HEADER, required = true, description = "customerId", example = "30241106")
    @Parameter(name = "placeholder", in = ParameterIn.PATH, required = true, description = "placeholder", example = "PIGGY_1")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Criado com sucesso")
    })
    @PatchMapping("/placeholder/{placeholder}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Void> update(
            @RequestHeader("customer-id") final String customerId,
            @PathVariable("placeholder") final Placeholder placeholder,
            @RequestBody PiggyRequest piggyRequest
    );
}
