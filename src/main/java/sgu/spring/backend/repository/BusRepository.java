package sgu.spring.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sgu.spring.backend.model.Bus;

import java.util.List;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {

    @Query("SELECT b FROM Bus b WHERE b.enable = true")
    List<Bus> findAllActive();
}
