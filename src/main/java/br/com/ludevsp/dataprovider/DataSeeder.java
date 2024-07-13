package br.com.ludevsp.dataprovider;

import br.com.ludevsp.domain.PreferenceEnum;
import br.com.ludevsp.domain.entities.Preference;
import br.com.ludevsp.domain.interfaces.repositories.PreferenceRepository;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder {
    private PreferenceRepository repository;

    public DataSeeder(PreferenceRepository repository) {
        this.repository = repository;
    }

    public void seedDataPreference() {
        if(repository.count() == PreferenceEnum.values().length) {
            return;
        }

        for (PreferenceEnum preference : PreferenceEnum.values()) {
            repository.save(new Preference(preference.getId(), preference.getName()));
        }
    }
}
