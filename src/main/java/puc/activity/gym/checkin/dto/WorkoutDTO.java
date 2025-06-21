package puc.activity.gym.checkin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import puc.activity.gym.checkin.model.RunningMetadata;
import puc.activity.gym.checkin.model.StrengthMetadata;

import java.time.Duration;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class WorkoutDTO {
    private Long workoutId;
    private Long userId;
    private Duration duration;

    private RunningMetadata runningMetadata;
    private StrengthMetadata strengthMetadata;
}
