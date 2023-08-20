package com.antonio.cashbox.entrypoint.controller;

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

    @Operation(summary = "CASH BOX - insert")
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

    @Operation(summary = "CASH BOX - findByCustomerId")
    @Parameter(name = "customer-id", in = ParameterIn.HEADER, required = true, description = "customerId", example = "30241106")
    @Parameter(name = "typeBox", in = ParameterIn.HEADER, required = false, description = "typeBox", example = "PIGGY")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Buscado com sucesso")
    })
    @GetMapping("/customerId")
    @ResponseStatus(HttpStatus.OK)
    List<CashBoxResponse> findByCustomerId(
            @RequestHeader("customer-id") final String customerId,
            @RequestHeader(value = "typeBox", required = false) final TypeBox typeBox

    );

    @Operation(summary = "CASH BOX - updateAmount")
    @Parameter(name = "customer-id", in = ParameterIn.HEADER, required = true, description = "customerId", example = "30241106")
    @Parameter(name = "nameBox", in = ParameterIn.HEADER, required = false, description = "nameBox", example = "PIGGY_1")
    @Parameter(name = "amount", in = ParameterIn.HEADER, required = false, description = "amount", example = "0")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Alterado com sucesso")
    })
    @PatchMapping("/update-amount")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Void> updateAmount(
            @RequestHeader("customer-id") final String customerId,
            @RequestHeader("nameBox") final String nameBox,
            @RequestHeader("amount") final BigDecimal amount
    );

    @Operation(summary = "CASH BOX - update-amount-blocked")
    @Parameter(name = "customer-id", in = ParameterIn.HEADER, required = true, description = "customerId", example = "30241106")
    @Parameter(name = "nameBox", in = ParameterIn.HEADER, required = false, description = "nameBox", example = "PIGGY_1")
    @Parameter(name = "amountBlocked", in = ParameterIn.HEADER, required = false, description = "amountBlocked", example = "0")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Alterado com sucesso")
    })
    @PatchMapping("/update-amount-blocked")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Void> updateAmountBlocked(
            @RequestHeader("customer-id") final String customerId,
            @RequestHeader("nameBox") final String nameBox,
            @RequestHeader("amountBlocked") final BigDecimal amountBlocked
    );
}
