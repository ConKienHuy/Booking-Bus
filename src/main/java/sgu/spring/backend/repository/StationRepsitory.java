package sgu.spring.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sgu.spring.backend.model.Station;

@Repository
public interface StationRepsitory extends JpaRepository<Station, Long> {
}
