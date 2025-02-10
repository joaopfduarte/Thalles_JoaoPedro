package br.cefet.hotelaria.controller;

import br.cefet.hotelaria.service.ReservationService;
import br.cefet.hotelaria.domain.Reservation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = ("http://localhost:3001"))
@RestController
@RequestMapping("api/v1/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getById(@PathVariable Long id) {
        return ResponseEntity.ok().body(reservationService.getById(id));
    }

    @GetMapping({ "", "/" })
    public ResponseEntity<List<Reservation>> getAll() {
        return ResponseEntity.ok().body(reservationService.getAll());
    }

    @GetMapping({ "/searchText/{searchText}" })
    public ResponseEntity<List<Reservation>> getBySearchText(@PathVariable String searchText) {
        return ResponseEntity.ok().body(reservationService.getBySearchText(searchText));
    }

    @GetMapping({ "/searchText", "/searchText/" })
    public ResponseEntity<List<Reservation>> getBySearchText() {
        return ResponseEntity.ok().body(new ArrayList<>());
    }

    @GetMapping("/teste")
    public String teste() {
        return "Teste";
    }

    @PostMapping({ "", "/" })
    public ResponseEntity<Reservation> create(@Valid @RequestBody Reservation reservation) {
        reservation = reservationService.create(reservation);
        return ResponseEntity.ok().body(reservation);
    }

    @PutMapping({ "", "/" })
    public ResponseEntity<Reservation> update(@Valid @RequestBody Reservation reservation) {
        reservation = reservationService.update(reservation); 
        return ResponseEntity.ok().body(reservation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Reservation> update(@PathVariable Long id) {
        Reservation reservation = reservationService.delete(id);
        return ResponseEntity.ok().body(reservation);
    }

}
