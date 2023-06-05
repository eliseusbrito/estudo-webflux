package br.com.eliseubrito.estudowebflux.repository;

import br.com.eliseubrito.estudowebflux.entity.Pix;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PixRepository extends ReactiveMongoRepository<Pix, String>{


}
