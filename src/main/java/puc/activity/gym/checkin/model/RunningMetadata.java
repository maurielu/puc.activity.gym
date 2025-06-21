package puc.activity.gym.checkin.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class RunningMetadata extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long runningId;

    private Long steps;

    @NotNull
    private BigDecimal distance;

    @NotNull
    private DistanceUnit distanceUnit = DistanceUnit.KM;

    @OneToOne()
    @JoinColumn(name = "workoutId")
    @MapsId
    @JsonManagedReference("runningMetadata")
    @ToString.Exclude
    private Workout workout;

    public enum DistanceUnit {
        KM
    }
}

