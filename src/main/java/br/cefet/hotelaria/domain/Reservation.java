package br.cefet.hotelaria.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "reservation")

public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Tipo é campo obrigatório para inserir ou modificar um SERVICE")
    @Column(nullable = false, length = 50)
    private String responsavel;

    @NotEmpty
    @Column(nullable = false, length = 70)
    private String suite;

    @NotNull
    @Column(nullable = false, length = 70)
    private float valor;

}
