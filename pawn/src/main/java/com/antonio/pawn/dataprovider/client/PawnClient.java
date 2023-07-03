package com.antonio.pawn.dataprovider.client;

import com.antonio.pawn.dataprovider.client.response.PawnBlockedResponse;
import com.antonio.pawn.dataprovider.client.response.PawnResponse;
import com.antonio.pawn.dataprovider.client.response.PawnUnlockedResponse;
import com.antonio.pawn.dataprovider.client.response.PiggyResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple3;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Component
public class PawnClient {

    WebClient webClient = WebClient.create();

    public Flux<Tuple3<List<PiggyResponse>, List<PawnUnlockedResponse>, List<PawnBlockedResponse>>> find(String customerId) {



        // Endpoint 1
        ParameterizedTypeReference<List<PiggyResponse>> responseType = new ParameterizedTypeReference<List<PiggyResponse>>() {};
        String endpoint1Url = "http://localhost:8080/v1/piggy/customerId";

        Mono<List<PiggyResponse>> responsePiggy = webClient.get()
                .uri(endpoint1Url)
                .header("customer-id", customerId)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(responseType);

        // Endpoint 2
        ParameterizedTypeReference<List<PawnUnlockedResponse>> responsePawnType = new ParameterizedTypeReference<List<PawnUnlockedResponse>>() {};
        String endpoint2Url = "http://localhost:8081/v1/cashbox/customerId";
        Mono<List<PawnUnlockedResponse>> responsePawnAll = webClient.get()
                .uri(endpoint2Url)
                .header("customer-id", customerId)
                .header("typeBox", "PIGGY")
                //.header("statusBox", "UNLOCKED")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(responsePawnType);

        // Endpoint 3
        ParameterizedTypeReference<List<PawnBlockedResponse>> responsePawnBType = new ParameterizedTypeReference<List<PawnBlockedResponse>>() {};
        String endpoint3Url = "http://localhost:8081/v1/cashbox/customerId";
        Mono<List<PawnBlockedResponse>> responsePawnBlocked = webClient.get()
                .uri(endpoint3Url)
                .header("customer-id", customerId)
                .header("typeBox", "PIGGY")
                .header("statusBox", "BLOCKED")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(responsePawnBType);


        Flux<Tuple3<List<PiggyResponse>, List<PawnUnlockedResponse>, List<PawnBlockedResponse>>> combinedFlux = Flux.zip(responsePiggy, responsePawnAll, responsePawnBlocked);


        combinedFlux.subscribe(tuple3 -> {
            String response1 = tuple3.getT1().toString();
            String response2 = tuple3.getT2().toString();
            String response3 = tuple3.getT3().toString();

            System.out.println("Data from endpoints:");
            System.out.println("Response from endpoint 1: " + response1);
            System.out.println("Response from endpoint 2: " + response2);
            System.out.println("Response from endpoint 3: " + response3);

        });


        return combinedFlux;
    }
}





//    private final WebClient webClient;
//    private final String pawnPath = "Pawn";
//
//    public PawnClient(final long timeout) {
//
//        HttpClient httpClient = HttpClient.create()
//                .doOnConnected(connect -> connect.addHandlerLast(new ReadTimeoutHandler(timeout, TimeUnit.MILLISECONDS)));
//
//        String baseUrl = "Pawn";
//
//        this.webClient = WebClient.builder()
//                .baseUrl(baseUrl)
//                .clientConnector(new ReactorClientHttpConnector(httpClient))
//                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
//                .build();
//
//    }
//
//    public PawnBlockedResponse getPawn(final String customerId) {
//        return webClient.get()
//                .uri(uriBuilder -> uriBuilder
//                        .path(pawnPath)
//                        .build())
//                .header("customerId", customerId)
//                .retrieve()
//                .onStatus(httpStatus -> !httpStatus.is2xxSuccessful(), clientResponse -> Mono.error(new IllegalArgumentException("ERRROR")))
//                .bodyToMono(PawnBlockedResponse.class)
//                .onErrorMap(throwable -> throwable.getCause() instanceof WebClientRequestException, ex -> new IllegalArgumentException("ERRROR"))
//                .doOnError(throwable -> {
//                    log.error("Falha na comunicação : [{}]", customerId);
//                })
//                .doOnSuccess(pawnResponse -> log.info("Sucesso na comunicação: [{}]", customerId))
//                .block();
//    }
