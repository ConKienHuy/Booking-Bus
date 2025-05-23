package sgu.spring.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "seat")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String seatType;

    private double seatPrice;

    private boolean enable;

    private LocalDateTime  createdAt;

    private LocalDateTime updatedAt;
}
