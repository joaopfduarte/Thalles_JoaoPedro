package br.cefet.hotelaria.controller;

import br.cefet.hotelaria.service.GuestService;
import br.cefet.hotelaria.domain.Guest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/guest")
public class GuestController {
    private final GuestService guestService;

    @Autowired
    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Guest> getById(@PathVariable Long id) {
        return ResponseEntity.ok().body(guestService.getById(id));
    }

    @GetMapping({ "", "/" })
    public ResponseEntity<List<Guest>> getAll() {
        return ResponseEntity.ok().body(guestService.getAll());
    }

    @GetMapping({ "/searchText/{searchText}" })
    public ResponseEntity<List<Guest>> getBySearchText(@PathVariable String searchText) {
        return ResponseEntity.ok().body(guestService.getBySearchText(searchText));
    }

    @GetMapping({ "/searchText", "/searchText/" })
    public ResponseEntity<List<Guest>> getBySearchText() {
        return ResponseEntity.ok().body(new ArrayList<>());
    }

    @GetMapping("/teste")
    public String teste() {
        return "Teste";
    }

    @PostMapping({ "", "/" })
    public ResponseEntity<Guest> create(@Valid @RequestBody Guest guest) {
        guest = guestService.create(guest);
        return ResponseEntity.ok().body(guest);
    }

    @PutMapping({ "", "/" })
    public ResponseEntity<Guest> update(@Valid @RequestBody Guest guest) {
        guest = guestService.update(guest);
        return ResponseEntity.ok().body(guest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Guest> update(@PathVariable Long id) {
        Guest guest = guestService.delete(id);
        return ResponseEntity.ok().body(guest);
    }
}
