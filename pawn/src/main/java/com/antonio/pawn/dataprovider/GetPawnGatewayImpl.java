package com.antonio.pawn.dataprovider;

import com.antonio.pawn.core.dataprovider.GetPawnGateway;
import com.antonio.pawn.core.domain.PawnAvailableDomain;
import com.antonio.pawn.core.enumeration.TypeBox;
import com.antonio.pawn.core.enumeration.TypePawn;
import com.antonio.pawn.dataprovider.client.CashBoxClient;
import com.antonio.pawn.dataprovider.client.PiggyClient;
import com.antonio.pawn.dataprovider.client.response.CashBoxResponse;
import com.antonio.pawn.dataprovider.client.response.PiggyResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class GetPawnGatewayImpl implements GetPawnGateway {

    private final CashBoxClient cashBoxClient;
    private final PiggyClient piggyClient;

    @Override
    public PawnAvailableDomain getPawnAvailable(String customerId) {
        return getPawnAvailableDomain(customerId);
    }

    private PawnAvailableDomain getPawnAvailableDomain(final String customerId) {
        var piggyList = piggyClient.getPiggy(customerId);
        var cashBoxList = cashBoxClient.getCashBox(customerId);

        Flux<Tuple2<List<PiggyResponse>, List<CashBoxResponse>>> response = Flux.zip(piggyList, cashBoxList);
        final var pawns = getPawns(response);
        log.info("Sucesso tuple [{}]", customerId);

        return pawns;
    }

    private PawnAvailableDomain getPawns(final Flux<Tuple2<List<PiggyResponse>, List<CashBoxResponse>>> response) {

        return response
                .flatMap(tuple -> Flux.just(tuple.getT1()).zipWith(Flux.just(tuple.getT2())))
                .map(tuple -> {
                    List<PiggyResponse> piggyResponseList = tuple.getT1();
                    List<CashBoxResponse> cashBoxResponseList = tuple.getT2();

                    var listPiggy = piggyResponseList.stream()
                            .filter(p -> p.placeholder().startsWith("PIGGY"))
                            .toList();

                    var listCashBoxBalancePositive = cashBoxResponseList.stream()
                            .filter(c -> c.selectBalance().accountBalance().compareTo(BigDecimal.ZERO) > 0)
                            .filter(c -> c.typeBox().equals(TypeBox.PIGGY))
                            .toList();

                    List<PawnAvailableDomain.PawnDomain> listPawnAvailable = listPiggy.stream()
                            .flatMap(p -> listCashBoxBalancePositive.stream()
                                    .filter(c -> c.nameBox().equals(p.placeholder()))
                                    .map(c -> PawnAvailableDomain.PawnDomain.builder()
                                            .name(p.namePiggy())
                                            .balance(c.selectBalance().accountBalance())
                                            .nameBox(c.nameBox())
                                            .typePawn(TypePawn.Box)
                                            .build()))
                            .toList();

                    return PawnAvailableDomain.builder()
                            .totalAvailableBalance(listPawnAvailable.stream()
                                    .map(PawnAvailableDomain.PawnDomain::balance)
                                    .reduce(BigDecimal.ZERO, BigDecimal::add))
                            .pawns(listPawnAvailable.stream()
                                    .sorted(Comparator.comparing(PawnAvailableDomain.PawnDomain::balance)
                                            .reversed())
                                    .toList())
                            .build();
                }).blockFirst();
    }
}
