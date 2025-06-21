package puc.activity.gym.checkin.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class StrengthMetadata {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long strengthId;

    @OneToOne
    @JoinColumn(name = "workoutId")
    private Workout workout;
}
