package br.com.ludevsp.domain.interfaces.repositories;


import br.com.ludevsp.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {
    User findByUserId(long id);

    User findByEmail(String email);
}
