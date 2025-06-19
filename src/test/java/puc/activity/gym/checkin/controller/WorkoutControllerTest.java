package puc.activity.gym.checkin.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import puc.activity.gym.checkin.CheckinApplicationTests;
import puc.activity.gym.checkin.model.Workout;

public class WorkoutControllerTest extends CheckinApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @Rollback
    void insertWorkout() {
        Workout newWorkout = new Workout();
        newWorkout.setUserId(1L);
        ResponseEntity<Workout> response = restTemplate.postForEntity(BASE_URL + port + "/workout", newWorkout, Workout.class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    @Rollback
    void findList() {
        insertWorkout();
        ResponseEntity<String> response = restTemplate.getForEntity(BASE_URL + port + "/workout/list", String.class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }
}
