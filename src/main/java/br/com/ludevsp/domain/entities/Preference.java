package br.com.ludevsp.domain.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "preference")
@NoArgsConstructor
@Data
public class Preference {
    @Id
    @Column(name = "preference_id")
    private Byte preferenceId;

    @Column(name = "name", nullable = false)
    private String name;

    public Preference(Byte preferenceId, String name) {
        this.preferenceId = preferenceId;
        this.name = name;
    }
}