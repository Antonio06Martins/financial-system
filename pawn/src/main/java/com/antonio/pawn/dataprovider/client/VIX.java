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
