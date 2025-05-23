package sgu.spring.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sgu.spring.backend.model.StationSchedule;

import java.util.List;

@Repository
public interface StationScheduleRepository extends JpaRepository<StationSchedule, Long> {

    @Query("SELECT s FROM StationSchedule s WHERE s.enable = true")
    List<StationSchedule> findAllActive();
}
