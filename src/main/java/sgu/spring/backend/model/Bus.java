package sgu.spring.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "bus")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String busCode;

    private String numberPlate;

    @OneToMany(mappedBy = "bus")
    private List<BusSeat> busSeat;

    private int status;

    private LocalDateTime  createdAt;

    private LocalDateTime updatedAt;
}
