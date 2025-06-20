package puc.activity.gym.checkin.model.decorator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleWorkoutMetadata implements WorkoutMetadata {
    Duration duration;
}
