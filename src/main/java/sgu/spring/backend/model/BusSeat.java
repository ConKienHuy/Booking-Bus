package sgu.spring.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "bus_seat")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BusSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "busId")
    private Bus bus;

    @ManyToOne
    @JoinColumn(name = "seatId")
    private Seat seat;

    private String seatNumber;

    private boolean enable;

    private LocalDateTime createdAt;

    private LocalDateTime  updatedAt;
}
