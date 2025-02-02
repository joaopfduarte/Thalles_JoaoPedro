package br.cefet.hotelaria.service;

import br.cefet.hotelaria.domain.Guest;
import br.cefet.hotelaria.domain.Reservation;
import br.cefet.hotelaria.repository.GuestRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GuestService {
    private final GuestRepository guestRepository;

    public Guest getById(Long id) {
        Guest guest = guestRepository.findById(id).orElse(null);
        if (guest == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Service não encontrado");
        }

        return guest;
    }

    public Guest create(Guest guest) {
        if (guest != null && guest.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id não deve ser informado");
        }

        // Link the reservations to the guest before saving
        if (guest.getReservations() != null) {
            for (Reservation reservation : guest.getReservations()) {
                reservation.setGuest(guest); // Make sure the guest is set in each reservation
            }
        }

        guest = guestRepository.save(guest); // Save the guest along with the reservations
        return guest;
    }

    public List<Guest> getAll() {
        return guestRepository.findAll();
    }

    public Guest update(Guest guest) {
        if (guest != null && guest.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id deve ser informado");
        }

        // Ensure the guest is associated with each reservation during update
        if (guest.getReservations() != null) {
            for (Reservation reservation : guest.getReservations()) {
                reservation.setGuest(guest); // Set the guest in each reservation
            }
        }

        guest = guestRepository.save(guest); // Save the updated guest
        return guest;
    }

    public Guest delete(Long id) {
        Guest guest = this.getById(id);
        if (guest == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Service (" + id + ") não encontrado para exclusão.");
        }

        guestRepository.delete(guest);
        return guest;
    }

    public List<Guest> getBySearchText(String searchText) {
        List<Guest> guestList = guestRepository.findByNomeContaining(searchText);
        if (guestList == null) {
            guestList = new ArrayList<>();
        }
        return guestList;
    }
}
