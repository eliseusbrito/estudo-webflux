package br.com.eliseubrito.estudowebflux.service;

import br.com.eliseubrito.estudowebflux.entity.Pix;
import br.com.eliseubrito.estudowebflux.repository.PixRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class PixService {

    private final PixRepository repository;

    public PixService(PixRepository repository) {
        this.repository = repository;
    }

    public Flux<Pix> findAll(){
        return repository.findAll();
    }

    public Mono<Pix> save(Pix pix) {
        pix.setCreatedDate(LocalDateTime.now());
        return repository.save(pix);
    }

}
