package sgu.spring.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sgu.spring.backend.model.Station;

import java.util.List;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {

    @Query("SELECT s FROM Station s WHERE s.enable = true")
    List<Station> findAllActive();
}
