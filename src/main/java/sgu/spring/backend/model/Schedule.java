package sgu.spring.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "schedule")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "busId")
    private Bus bus;

    @ManyToOne
    @JoinColumn(name = "stationFromId", referencedColumnName = "id")
    private Station stationFrom;

    @ManyToOne
    @JoinColumn(name = "stationToId", referencedColumnName = "id")
    private Station stationTo;

    private boolean enable;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
