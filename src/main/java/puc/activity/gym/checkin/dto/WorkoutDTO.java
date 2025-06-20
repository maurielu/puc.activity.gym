package puc.activity.gym.checkin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import puc.activity.gym.checkin.pojo.WorkoutMetaData;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WorkoutDTO {
    private Long workoutId;

    private Long userId;
    private String userName;

    private WorkoutMetaData workoutMetaData = new WorkoutMetaData();
}
