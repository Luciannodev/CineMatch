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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "preference_id")
    private Byte preferenceId;

    @Column(name = "name", nullable = false)
    private String name;
}
