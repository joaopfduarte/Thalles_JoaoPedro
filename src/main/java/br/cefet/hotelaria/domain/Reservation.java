package br.cefet.hotelaria.domain;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

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

    @NotNull(message = "A data de reserva é obrigatória")
    @Column(name = "data_reserva", nullable = false)
    private LocalDate dataReserva;

    @NotNull(message = "O horário da reserva é obrigatório")
    @Column(name = "horario_reserva", nullable = false)
    private LocalTime horarioReserva;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "guest_id", nullable = false)
    private Guest guest;
}
