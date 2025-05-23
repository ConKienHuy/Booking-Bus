package sgu.spring.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

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

    @OneToMany(mappedBy = "schedule")
    private List<StationSchedule> scheduleList;

    private boolean enable;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
