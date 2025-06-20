package puc.activity.gym.checkin.model.decorator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Duration;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RunningWorkoutMetadata implements WorkoutMetadata {
    Duration duration;
    BigDecimal distance;
}
