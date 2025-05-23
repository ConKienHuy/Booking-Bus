package sgu.spring.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    private boolean enable;

    private LocalDateTime  createdAt;

    private LocalDateTime updatedAt;
}
