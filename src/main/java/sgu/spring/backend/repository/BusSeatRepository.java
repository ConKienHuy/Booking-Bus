package sgu.spring.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sgu.spring.backend.model.BusSeat;

import java.util.List;

@Repository
public interface BusSeatRepository extends JpaRepository<BusSeat, Long> {

    @Query("SELECT b FROM BusSeat b WHERE b.enable = true")
    List<BusSeat> findAllActive();
}
