package com.antonio.pawn.dataprovider.client;

import com.antonio.pawn.dataprovider.client.response.CashBoxResponse;
import io.netty.handler.timeout.ReadTimeoutHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class CashBoxClient {

    private final WebClient webClient;
    private final String baseUrl = "http://localhost:8081";
    private final String path = "/v1/cashbox/customerId";
    private final long timeout = 1500;

    public CashBoxClient() {
        HttpClient httpclient = HttpClient.create()
                .doOnConnected(connect -> connect.addHandlerLast(new ReadTimeoutHandler(timeout, TimeUnit.MILLISECONDS)));

        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .clientConnector(new ReactorClientHttpConnector(httpclient))
                .defaultHeader (HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build();

    }

    public Mono<List<CashBoxResponse>> getCashBox(final String customerId) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(path)
                        .build())
                .header("customer-id", customerId)
                .retrieve()
                .onStatus(httpStatus -> !httpStatus.is2xxSuccessful(), clientResponse -> Mono.error(new IllegalArgumentException("ERRROR")))
                .bodyToMono(new ParameterizedTypeReference<List<CashBoxResponse>>() {
                })
                .onErrorMap(throwable -> throwable.getCause() instanceof WebClientRequestException, ex -> new IllegalArgumentException("ERRROR"))
                .doOnError(throwable -> {
                    log.error("Falha na comunicação : [{}]", customerId);
                })
                .doOnSuccess(pawnResponse -> log.info("Sucesso na comunicação com ms-cash-box: [{}]", customerId));
    }
}
