package sgu.spring.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sgu.spring.backend.enums.EPickUpType;

import java.time.LocalDateTime;

@Entity
@Table(name = "station_schedule")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StationSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name= "scheduleId")
    private Schedule schedule;

    @OneToOne
    @JoinColumn(name = "stationId")
    private Station station;

    private LocalDateTime timeArrival;

    private EPickUpType pickUpType;

    private boolean enable;

}
