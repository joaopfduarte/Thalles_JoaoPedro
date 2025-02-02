package br.cefet.hotelaria.service;

import br.cefet.hotelaria.domain.Reservation;
import br.cefet.hotelaria.repository.ReservationRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public Reservation getById(Long id) {
        Reservation reservation = reservationRepository.findById(id).orElse(null);
        if (reservation == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Service não encontrado");
        }

        return reservation;
    }

    public Reservation create(Reservation reservation) {
        if (reservation == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A reserva não pode ser nula");
        }
    
        if (reservation.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id não deve ser informado");
        }
    
        if (reservation.getGuest() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Deve especificar o hóspede que fez a reserva");
        }
    
        validarHorarioReserva(reservation.getHorarioReserva());
    
        return reservationRepository.save(reservation);
    }

    public Reservation update(Reservation reservation) {
        if (reservation != null && reservation.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id deve ser informado");
        }

        validarHorarioReserva(reservation.getHorarioReserva());

        reservation = reservationRepository.save(reservation);
        return reservation;
    }

    private void validarHorarioReserva(LocalTime horarioReserva) {
        if (horarioReserva == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Horário da reserva não pode ser nulo.");
        }

        LocalTime horaMinima = LocalTime.of(6, 0); // 06:00
        LocalTime horaMaxima = LocalTime.of(23, 0); // 23:00

        if (horarioReserva.isBefore(horaMinima) || horarioReserva.isAfter(horaMaxima)) {
            throw new IllegalArgumentException("Reservas só podem ser feitas entre 06:00 e 23:00.");

        }
    }

    public Reservation delete(Long id) {
        Reservation reservation = this.getById(id);
        if (reservation == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Service (" + id + ") não encontrado para exclusão.");
        }

        reservationRepository.delete(reservation);
        return reservation;
    }

    public List<Reservation> getBySearchText(String searchText) {
        List<Reservation> reservationList = reservationRepository.findByResponsavelContaining(searchText);
        if (reservationList == null) {
            reservationList = new ArrayList<>();
        }
        return reservationList;
    }

    public List<Reservation> getAll() {
        return reservationRepository.findAll();
    }
    

}
