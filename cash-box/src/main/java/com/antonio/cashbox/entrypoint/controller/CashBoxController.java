package com.antonio.cashbox.entrypoint.controller;

import com.antonio.cashbox.core.enumeration.StatusBox;
import com.antonio.cashbox.core.enumeration.TypeBox;
import com.antonio.cashbox.entrypoint.controller.request.CashBoxRequest;
import com.antonio.cashbox.entrypoint.controller.response.CashBoxResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Tag(name = "CASH-BOX")
@RequestMapping("/v1/cashbox")
public interface CashBoxController {

    @Operation(summary = "CASH BOX")
    @Parameter(name = "customer-id", in = ParameterIn.HEADER, required = true, description = "customerId", example = "30241106")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Criado com sucesso")
    })

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<Void> insert(
            @RequestHeader("customer-id") final String customerId,
            @RequestBody CashBoxRequest cashBoxRequest
    );

    @Operation(summary = "CASH BOX")
    @Parameter(name = "customer-id", in = ParameterIn.HEADER, required = true, description = "customerId", example = "30241106")
    @Parameter(name = "typeBox", in = ParameterIn.HEADER, required = true, description = "typeBox", example = "PIGGY")
    @Parameter(name = "statusBox", in = ParameterIn.HEADER, required = false, description = "statusBox", example = "UNLOCKED")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Buscado com sucesso")
    })
    @GetMapping("/customerId")
    @ResponseStatus(HttpStatus.OK)
    List<CashBoxResponse> findByCustomerId(
            @RequestHeader("customer-id") final String customerId,
            @RequestHeader("typeBox") final TypeBox typeBox,
            @RequestHeader(value = "statusBox", required = false) final StatusBox statusBox

    );

    @Operation(summary = "Piggy")
    @Parameter(name = "customer-id", in = ParameterIn.HEADER, required = true, description = "customerId", example = "30241106")
    @Parameter(name = "nameBox", in = ParameterIn.HEADER, required = false, description = "nameBox", example = "PIGGY_1")
    @Parameter(name = "amount", in = ParameterIn.HEADER, required = false, description = "amount", example = "0")
    @Parameter(name = "amountBlocked", in = ParameterIn.HEADER, required = false, description = "amountBlocked", example = "0")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Alterado com sucesso")
    })
    @PatchMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Void> update(
            @RequestHeader("customer-id") final String customerId,
            @RequestHeader("nameBox") final String nameBox,
            @RequestHeader("amount") final BigDecimal amount,
            @RequestHeader("amountBlocked") final BigDecimal amountBlocked
    );
}
