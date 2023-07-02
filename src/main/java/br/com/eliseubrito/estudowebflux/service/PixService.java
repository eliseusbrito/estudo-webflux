package br.com.eliseubrito.estudowebflux.service;

import br.com.eliseubrito.estudowebflux.entity.Pix;
import br.com.eliseubrito.estudowebflux.repository.PixRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@Slf4j
public class PixService {

    private final PixRepository repository;

    public PixService(PixRepository repository) {
        this.repository = repository;
    }

    public Flux<Pix> findAll(){
        return repository.findAll();
    }

    public Mono<Pix> findById(String id){
        return repository.findById(id).log();
    }

//    public Mono<Pix> findById(String id){
////        return repository.findOne(id);
//    }

    public Mono<Pix> save(Pix pix) {
        pix.setCreatedDate(LocalDateTime.now());
        return repository.save(pix);
    }

}
