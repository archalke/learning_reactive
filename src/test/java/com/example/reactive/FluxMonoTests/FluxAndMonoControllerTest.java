package com.example.reactive.FluxMonoTests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@WebFluxTest
class FluxAndMonoControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @Test
    public void flux_approach1(){

        Flux<Integer> integerFlux = webTestClient.get().uri("/flux")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .returnResult(Integer.class)
                .getResponseBody();

        StepVerifier.create(integerFlux)
                .expectSubscription()
                .expectNext(1)
                .expectNext(2)
                .expectNext(3)
                .expectNext(4)
                .verifyComplete();


    }

    @Test
    public void flux_approach2 (){

        webTestClient.get().uri("/flux")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(Integer.class)
                .hasSize(5);
    }

    @Test
    public void flux_approach3(){

        List<Integer> expectedIntegerList = Arrays.asList(1,2,3,4);

         EntityExchangeResult<List<Integer>> entityExchangeResult = webTestClient.get().uri("/flux")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Integer.class)
                .returnResult();

        System.out.println("flux approach 3");
        assertEquals(entityExchangeResult,entityExchangeResult);

    }

    @Test
    public void fluxStream(){

        Flux<Long> longFluxStream = webTestClient.get().uri("/fluxstream")
                .accept(MediaType.APPLICATION_STREAM_JSON)
                .exchange()
                .expectStatus().isOk()
                .returnResult(Long.class)
                .getResponseBody();

        StepVerifier.create(longFluxStream)
                .expectNext(0l)
                .expectNext(1l)
                .expectNext(2l)
                .thenCancel()
                .verify();

    }

    @Test
    public void mono(){

        Integer expectedValue = 1;// new Integer(1);

        webTestClient.get().uri("/mono")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Integer.class)
                .consumeWith(
                        response -> {
                            assertEquals(expectedValue,response.getResponseBody());
                        }
                );
    }


}
