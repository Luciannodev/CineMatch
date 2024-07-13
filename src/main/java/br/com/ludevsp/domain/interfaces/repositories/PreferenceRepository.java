package br.com.ludevsp.domain.interfaces.repositories;

import br.com.ludevsp.domain.entities.Preference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PreferenceRepository extends JpaRepository<Preference, String>, JpaSpecificationExecutor<Preference> {
}
