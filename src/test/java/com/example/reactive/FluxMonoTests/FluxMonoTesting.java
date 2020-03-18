package com.example.reactive.FluxMonoTests;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class FluxMonoTesting {
/*
    @Test
    public void fluxTest(){

        Flux<String> stringFlux = Flux.just("a","b","c")
//                .concatWith(Flux.error(new RuntimeException("Exception Occurred")))
                .concatWithValues("aaa")

                .log();

        stringFlux
                .subscribe(System.out::println,
                        e->System.err.println(e),
                        ()->System.out.println("Completed"));


    }

    @Testx
    public void fluxTestElements_WithoutError(){

        Flux<String> stringFlux = Flux.just("a","b","c")
                .log();

        StepVerifier.create(stringFlux)
                .expectNext("a","b","c")
                .verifyComplete();
    }

*/
    @Test
    public void fluxTestElements_WithError(){

        Flux<String> stringFlux = Flux.just("a","b","c")
                .concatWith(Flux.error(new RuntimeException("Error test")))
                .log();

        StepVerifier.create(stringFlux)
                .expectNext("a","b","c")
                .expectError();

    }


    @Test
    public void MonoTest(){
        Mono<String> stringMono = Mono.just("a");

        StepVerifier.create(stringMono)
                .expectNext("a")
                .verifyComplete();

    }



    @Test
    public void MonoTest_Error(){

//        Mono<String> stringMono = Mono.just("abc");

        StepVerifier.create(Mono.error(new RuntimeException("Exception Occurred")))
                .expectError(RuntimeException.class)
                .verify();

    }
}