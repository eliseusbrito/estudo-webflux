package br.com.eliseubrito.estudowebflux.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping(value = "/v1")
public class OperatorFluxController {

    @GetMapping(value = "/length-flux")
    public Flux<Integer> lengthFlux() {
        return Flux.just("Willian", "Suane", "Davi", "Oi")
                .map(n -> n.length())
                .doOnNext(System.out::println)
                .log();
    }

}
