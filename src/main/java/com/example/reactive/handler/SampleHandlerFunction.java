package com.example.reactive.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class SampleHandlerFunction {

    public Mono<ServerResponse> flux(ServerRequest serverRequest){

        return ServerResponse.ok()
                .body(
                        Flux.just(1,2,3,4)
                        .log(), Integer.class
                );

    }

    public Mono<ServerResponse> mono(ServerRequest serverRequest){

        return ServerResponse.ok()
                .body(
                        Mono.just(153)
                        .log(),Integer.class
                );

    }


}
