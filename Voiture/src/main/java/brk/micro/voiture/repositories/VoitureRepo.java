package brk.micro.voiture.repositories;


import brk.micro.voiture.entities.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoitureRepo extends JpaRepository<Voiture, Long> {
    List<Voiture> findAllByClientId(Long id);
}
