package puc.activity.gym.checkin.model.decorator;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.time.Duration;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({//
        @JsonSubTypes.Type(value = SimpleWorkoutMetadata.class, name = "simpleWorkoutMetadata"),//
        @JsonSubTypes.Type(value = RunningWorkoutMetadata.class, name = "runningWorkoutMetadata"),//
})
public interface WorkoutMetadata {
    Duration getDuration();
}
