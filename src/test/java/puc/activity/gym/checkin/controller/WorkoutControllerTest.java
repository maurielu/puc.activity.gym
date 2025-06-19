package puc.activity.gym.checkin.controller;

import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Rollback;
import puc.activity.gym.checkin.CheckinApplicationTests;
import puc.activity.gym.checkin.model.Workout;

public class WorkoutControllerTest extends CheckinApplicationTests {

    @Test
    @Rollback
    void insertWorkout() {
        Workout newWorkout = new Workout();
        performPost("/workout", newWorkout, Workout.class);
    }
}
