package br.cefet.hotelaria.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "guest")
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Tipo é campo obrigatório para inserir ou modificar um SERVICE")
    @Column(nullable = false, length = 50)
    private String nome;

    @NotEmpty
    @Column(nullable = false, length = 70)
    private String suite;

    @NotNull
    @Column(nullable = false, length = 70)
    private Long cpf;

    @OneToMany(mappedBy = "guest", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Reservation> reservations;
}
