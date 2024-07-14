package br.com.ludevsp.domain.interfaces.repositories;

import br.com.ludevsp.domain.entities.UserMovie;
import br.com.ludevsp.domain.entities.UserMovieKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMovieRepository extends JpaRepository<UserMovie, UserMovieKey>, JpaSpecificationExecutor<UserMovie> {
    UserMovie findByUserIdAndMovieId(Long userId, Long movieId);

}
