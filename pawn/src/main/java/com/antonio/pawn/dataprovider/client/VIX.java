package com.antonio.pawn.dataprovider.client;

public class VIX {
}


//package com.antonio.pawn.dataprovider.client;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Component;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Flux;
//import reactor.util.function.Tuple3;
//
//@Slf4j
//@Component
//public class PawnClient {
//
//    WebClient webClient = WebClient.create();
//
//    public Flux<Tuple3<String, String, String>> find(String customerId) {
//
//        // Endpoint 1
//        String endpoint1Url = "http://localhost:8080/v1/piggy/customerId";
//        Flux<String> response1Flux = webClient.get()
//                .uri(endpoint1Url)
//                .header("customer-id", customerId)
//                .accept(MediaType.APPLICATION_JSON)
//                .retrieve()
//                .bodyToFlux(String.class);
//        //.bodyToFlux(PiggyResponse[].class);
//
//        // Endpoint 2
//        String endpoint2Url = "http://localhost:8081/v1/cashbox/customerId";
//        Flux<String> response2Flux = webClient.get()
//                .uri(endpoint2Url)
//                .header("customer-id", customerId)
//                .header("typeBox", "PIGGY")
//                //.header("statusBox", "UNLOCKED")
//                .accept(MediaType.APPLICATION_JSON)
//                .retrieve()
//                .bodyToFlux(String.class);
//
//        // Endpoint 3
//        String endpoint3Url = "http://localhost:8081/v1/cashbox/customerId";
//        Flux<String> response3Flux = webClient.get()
//                .uri(endpoint3Url)
//                .header("customer-id", customerId)
//                .header("typeBox", "PIGGY")
//                .header("statusBox", "BLOCKED")
//                .accept(MediaType.APPLICATION_JSON)
//                .retrieve()
//                .bodyToFlux(String.class);
//
//
//            //Flux<Tuple3<PiggyResponse[], String, String>> combinedFlux = Flux.zip(response1Flux, response2Flux, response3Flux);
//            Flux<Tuple3<String, String, String>> combinedFlux = Flux.zip(response1Flux, response2Flux, response3Flux);
//
//            combinedFlux.subscribe(tuple3 -> {
//                //String response1 = Arrays.toString(tuple3.getT1());
//                String response1 = tuple3.getT1();
//                String response2 = tuple3.getT2();
//                String response3 = tuple3.getT3();
//
//                System.out.println("Data from endpoints:");
//                System.out.println("Response from endpoint 1: " + response1);
//                System.out.println("Response from endpoint 2: " + response2);
//                System.out.println("Response from endpoint 3: " + response3);
//
//            });
//
//
//        return combinedFlux;
//    }
//}

//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Mono;
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class PiggyClient {
//    private final WebClient webClient;
//
//    public PiggyClient() {
//        this.webClient = WebClient.create();
//    }
//
//    public Mono<List<Piggy>> find() {
//        return webClient.get()
//                .uri("https://api.example.com/piggy")
//                .retrieve()
//                .bodyToFlux(Piggy.class)
//                .collectList();
//    }
//
//    public Mono<List<WalletA>> findA() {
//        return webClient.get()
//                .uri("https://api.example.com/walletA")
//                .retrieve()
//                .bodyToFlux(WalletA.class)
//                .collectList();
//    }
//
//    public Mono<List<WalletB>> findB() {
//        return webClient.get()
//                .uri("https://api.example.com/walletB")
//                .retrieve()
//                .bodyToFlux(WalletB.class)
//                .collectList();
//    }
//
//    public Mono<List<NewObject>> process() {
//        Mono<List<Piggy>> piggyMono = find();
//        Mono<List<WalletA>> walletAMono = findA();
//        Mono<List<WalletB>> walletBMono = findB();
//
//        return Mono.zip(piggyMono, walletAMono, walletBMono)
//                .flatMap(tuple -> {
//                    List<Piggy> piggies = tuple.getT1();
//                    List<WalletA> walletAs = tuple.getT2();
//                    List<WalletB> walletBs = tuple.getT3();
//
//                    // Remover da lista WalletA os objetos que tenham o mesmo name da lista WalletB
//                    List<WalletA> filteredWalletAs = walletAs.stream()
//                            .filter(walletA -> walletBs.stream()
//                                    .noneMatch(walletB -> walletB.getName().equals(walletA.getName())))
//                            .collect(Collectors.toList());
//
//                    // Criar uma nova lista de objetos NewObject com os atributos name, apelido, balance
//                    List<NewObject> newObjects = piggies.stream()
//                            .flatMap(piggy -> filteredWalletAs.stream()
//                                    .filter(walletA -> piggy.getName().equals(walletA.getName()))
//                                    .map(walletA -> {
//                                        NewObject newObject = new NewObject();
//                                        newObject.setName(piggy.getName());
//                                        newObject.setApelido(piggy.getApelido());
//                                        newObject.setBalance(walletA.getBalance());
//                                        return newObject;
//                                    }))
//                            .collect(Collectors.toList());
//
//                    return Mono.just(newObjects);
//                });
//    }
//}

//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Mono;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class PiggyClient {
//    private final WebClient webClient;
//
//    public PiggyClient() {
//        this.webClient = WebClient.create();
//    }
//
//    public Mono<List<Piggy>> find() {
//        return webClient.get()
//                .uri("https://api.example.com/piggies")
//                .retrieve()
//                .bodyToFlux(Piggy.class)
//                .collectList();
//    }
//
//    public Mono<List<WalletA>> findA() {
//        return webClient.get()
//                .uri("https://api.example.com/wallets/a")
//                .retrieve()
//                .bodyToFlux(WalletA.class)
//                .collectList();
//    }
//
//    public Mono<List<WalletB>> findB() {
//        return webClient.get()
//                .uri("https://api.example.com/wallets/b")
//                .retrieve()
//                .bodyToFlux(WalletB.class)
//                .collectList();
//    }
//
//    public Mono<List<WalletC>> findC() {
//        Mono<List<Piggy>> piggyMono = find();
//        Mono<List<WalletA>> walletAMono = findA();
//        Mono<List<WalletB>> walletBMono = findB();
//
//        return Mono.zip(piggyMono, walletAMono, walletBMono)
//                .map(tuple -> {
//                    List<Piggy> piggies = tuple.getT1();
//                    List<WalletA> walletAs = tuple.getT2();
//                    List<WalletB> walletBs = tuple.getT3();
//
//                    List<String> duplicateNames = walletBs.stream()
//                            .map(WalletB::getName)
//                            .collect(Collectors.toList());
//
//                    return walletAs.stream()
//                            .filter(walletA -> !duplicateNames.contains(walletA.getName()))
//                            .map(walletA -> {
//                                Piggy matchingPiggy = piggies.stream()
//                                        .filter(piggy -> piggy.getName().equals(walletA.getName()))
//                                        .findFirst()
//                                        .orElse(null);
//
//                                return new WalletC(
//                                        walletA.getName(),
//                                        matchingPiggy != null ? matchingPiggy.getApelido() : "",
//                                        walletA.getBalance()
//                                );
//                            })
//                            .collect(Collectors.toList());
//                });
//    }
//}


//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Mono;
//import reactor.test.StepVerifier;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.mockito.Mockito.*;
//
//class PiggyClientTest {
//    @Mock
//    private WebClient webClient;
//
//    private PiggyClient piggyClient;
//
//    public PiggyClientTest() {
//        MockitoAnnotations.openMocks(this);
//        piggyClient = new PiggyClient(webClient);
//    }
//
//    @Test
//    void testFindC() {
//        List<Piggy> piggies = Arrays.asList(
//                new Piggy("Piggy 1", "Type 1", "Apelido 1"),
//                new Piggy("Piggy 2", "Type 2", "Apelido 2"),
//                new Piggy("Piggy 3", "Type 3", "Apelido 3")
//        );
//
//        List<WalletA> walletAs = Arrays.asList(
//                new WalletA("Piggy 1", "Type A", 100.0),
//                new WalletA("Piggy 2", "Type B", 200.0),
//                new WalletA("Piggy 4", "Type C", 300.0)
//        );
//
//        List<WalletB> walletBs = Arrays.asList(
//                new WalletB("Piggy 2", "Type X"),
//                new WalletB("Piggy 4", "Type Y")
//        );
//
//        when(webClient.get()).thenReturn(webClient);
//        when(webClient.uri(anyString())).thenReturn(webClient);
//        when(webClient.retrieve()).thenReturn(webClient);
//        when(webClient.bodyToFlux(Piggy.class)).thenReturn(Flux.fromIterable(piggies));
//        when(webClient.bodyToFlux(WalletA.class)).thenReturn(Flux.fromIterable(walletAs));
//        when(webClient.bodyToFlux(WalletB.class)).thenReturn(Flux.fromIterable(walletBs));
//
//        Mono<List<WalletC>> result = piggyClient.findC();
//
//        StepVerifier.create(result)
//                .expectNext(Arrays.asList(
//                        new WalletC("Piggy 1", "Apelido 1", 100.0),
//                        new WalletC("Piggy 4", "", 300.0)
//                ))
//                .verifyComplete();
//
//        verify(webClient, times(3)).get();
//        verify(webClient, times(3)).uri(anyString());
//        verify(webClient, times(3)).retrieve();
//        verify(webClient, times(1)).bodyToFlux(Piggy.class);
//        verify(webClient, times(1)).bodyToFlux(WalletA.class);
//        verify(webClient, times(1)).bodyToFlux(WalletB.class);
//    }
//}


//coloca apelido onde corresponde
//List<A> listA = new ArrayList<>(); // Sua lista original de objetos A
//
//List<B> resultList = listA.stream()
//    .flatMap(a -> a.bList.stream()
//        .filter(b -> b.name.equals(a.name))
//        .map(b -> {
//            b.setName(a.apelido);
//            return b;
//        }))
//    .collect(Collectors.toList());

//remove
//List<A> listA = new ArrayList<>(); // Sua lista original de objetos A
//List<B> listB = new ArrayList<>(); // Sua lista original de objetos B
//
//List<String> namesToRemove = listA.stream()
//    .map(A::getName)
//    .collect(Collectors.toList());
//
//List<B> filteredListB = listB.stream()
//    .filter(b -> !namesToRemove.contains(b.getName()))
//    .collect(Collectors.toList());



//BigDecimal totalBalance = listB.stream()
//    .map(B::getBalance)
//    .reduce(BigDecimal.ZERO, BigDecimal::add);