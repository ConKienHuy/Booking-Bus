package sgu.spring.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sgu.spring.backend.model.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
}
