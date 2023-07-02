package br.com.eliseubrito.estudowebflux.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/v1")
public class OperatorsMonoController {

    @GetMapping(value = "/just")
    public Mono<Integer> justMono() {
        return Mono.just(1).doOnSuccess(r -> System.out.println(r)).log();
    }


}
