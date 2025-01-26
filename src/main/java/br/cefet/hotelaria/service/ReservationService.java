package br.cefet.hotelaria.service;

import br.cefet.hotelaria.domain.Reservation;
import br.cefet.hotelaria.repository.ReservationRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;

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
        if (reservation != null && reservation.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id não deve ser informado");
        }

        reservation = reservationRepository.save(reservation);
        return reservation;
    }

    public List<Reservation> getAll() {
        return reservationRepository.findAll();
    }

    public Reservation update(Reservation reservation) {
        if (reservation != null && reservation.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id deve ser informado");
        }

        reservation = reservationRepository.save(reservation);
        return reservation;
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
        List<Reservation> reservationList = reservationRepository.findByIdContaining(searchText);
        if (reservationList == null) {
            reservationList = new ArrayList<>();
        }

        return reservationList;
    }
}
