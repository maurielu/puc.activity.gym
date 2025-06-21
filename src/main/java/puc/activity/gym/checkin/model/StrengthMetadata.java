package puc.activity.gym.checkin.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class StrengthMetadata {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long strengthId;

    @OneToOne()
    @JoinColumn(name = "workoutId")
    @MapsId
    @JsonManagedReference
    @ToString.Exclude
    private Workout workout;
}
