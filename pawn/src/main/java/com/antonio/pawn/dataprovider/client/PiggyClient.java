package com.antonio.pawn.dataprovider.client;

import com.antonio.pawn.dataprovider.client.response.PiggyResponse;
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
public class PiggyClient {

    private final WebClient webClient;
    private final String baseUrl = "http://localhost:8080";
    private final String path = "/v1/piggy/customerId";
    private final long timeout = 1500;

    public PiggyClient() {
        HttpClient httpclient = HttpClient.create()
                .doOnConnected(connect -> connect.addHandlerLast(new ReadTimeoutHandler(timeout, TimeUnit.MILLISECONDS)));

        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .clientConnector(new ReactorClientHttpConnector(httpclient))
                .defaultHeader (HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

        public Mono<List<PiggyResponse>> getPiggy(final String customerId) {
            return webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path(path)
                            .build())
                    .header("customer-id", customerId)
                    .retrieve()
                    .onStatus(httpStatus -> !httpStatus.is2xxSuccessful(), clientResponse -> Mono.error(new IllegalArgumentException("ERRROR")))
                    .bodyToMono(new ParameterizedTypeReference<List<PiggyResponse>>() {
                    })
                    .onErrorMap(throwable -> throwable.getCause() instanceof WebClientRequestException, ex -> new IllegalArgumentException("ERRROR"))
                    .doOnError(throwable -> {
                        log.error("Falha na comunicação : [{}]", customerId);
                    })
                    .doOnSuccess(pawnResponse -> log.info("Sucesso na comunicação com ms-piglet: [{}]", customerId));
        }
}
