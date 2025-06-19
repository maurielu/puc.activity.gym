package puc.activity.gym.checkin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import puc.activity.gym.checkin.model.Workout;
import puc.activity.gym.checkin.repository.WorkoutRepository;

@SpringBootTest
class CheckinApplicationTests {
    @Autowired
    WorkoutRepository workoutRepository;

    @Test
    void contextLoads() {
        Workout workout = new Workout();
        workout.setUserId(1L);
        workoutRepository.save(workout);

        System.out.println(workout);
    }

}
