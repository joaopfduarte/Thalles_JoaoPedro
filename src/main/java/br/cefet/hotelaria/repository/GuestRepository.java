package br.cefet.hotelaria.repository;

import br.cefet.hotelaria.domain.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {
    List<Guest> findByNomeContaining(String nome);

}
