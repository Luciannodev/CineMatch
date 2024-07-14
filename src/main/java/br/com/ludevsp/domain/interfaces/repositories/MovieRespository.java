package br.com.ludevsp.domain.interfaces.repositories;

import br.com.ludevsp.domain.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MovieRespository extends JpaRepository<Movie, String>, JpaSpecificationExecutor<Movie> {
}
