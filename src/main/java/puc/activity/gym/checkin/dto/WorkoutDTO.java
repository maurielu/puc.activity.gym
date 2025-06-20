package puc.activity.gym.checkin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import puc.activity.gym.checkin.model.decorator.SimpleWorkoutMetadata;
import puc.activity.gym.checkin.model.decorator.WorkoutMetadata;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class WorkoutDTO {
    private Long workoutId;

    private Long userId;
    private String userName;

    private WorkoutMetadata workoutMetaData = new SimpleWorkoutMetadata();
}
