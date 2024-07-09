package br.com.ludevsp.domain.interfaces.repositories;


import br.com.ludevsp.domain.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

@Component
public interface UserRepository extends JpaRepository<User, String> {
    User findByUserId(long id);

}
