package br.com.eliseubrito.estudowebflux.controller;

import br.com.eliseubrito.estudowebflux.entity.Pix;
import br.com.eliseubrito.estudowebflux.service.PixService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/v1")
public class PixController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PixController.class);
    private final PixService service;

    public PixController(PixService service) {
        this.service = service;
    }

    @GetMapping(value="/pix")
    public Flux<Pix> getAllPix(){
        return service.findAll();
    }

    @GetMapping(value="/pix/{id}")
    public Mono<Pix> findById(@PathVariable String id){
        return service.findById(id);
    }

    @GetMapping(value="/pix/count")
    public ResponseEntity<Flux<Pix>> getCount(){
//        Flux<Pix> all = service.findAll().log();
//        all.log();
//        all.subscribe();

//        Mono<Pix> allMono = service.findById().log();
//        all.subscribe();
//        LOGGER.info("all: {}", all);
//        List<Pix> collect = all.toStream().collect(Collectors.toList());
//        LOGGER.info(String.valueOf(all));
//        Collection<Pix> collect = all.toStream().collect(Collectors.toCollection());

//        LOGGER.debug("qualquer coisa");
//        LOGGER.info("Info: qualquer coisa: " + all);
        //long count = all.toStream().count();//Iterating over a toIterable() / toStream() is blocking, which is not supported in thread reactor-http-nio-3
//        Disposable subscribe = all.subscribe();
//        return ResponseEntity.status(HttpStatus.OK).body(collect);
//        return ResponseEntity.status(HttpStatus.OK).body(all);
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll().filter(payer -> payer.getPayer().equals("Eliseu n")).log());
    }

    @PostMapping(value="/pix")
    public Mono<Pix> postPix(@RequestBody Pix pix){
        return service.save(pix).log();
    }

}
