package puc.activity.gym.checkin.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import puc.activity.gym.checkin.CheckinApplicationTests;
import puc.activity.gym.checkin.dto.WorkoutDTO;
import puc.activity.gym.checkin.model.decorator.RunningWorkoutMetadata;
import puc.activity.gym.checkin.model.decorator.SimpleWorkoutMetadata;

import java.math.BigDecimal;

public class WorkoutControllerTest extends CheckinApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @Commit
    void insertWorkout() {
        WorkoutDTO newWorkoutDTO = new WorkoutDTO();
        newWorkoutDTO.setUserId(1L);
        SimpleWorkoutMetadata metadata = new SimpleWorkoutMetadata();
        newWorkoutDTO.setWorkoutMetaData(metadata);
        ResponseEntity<WorkoutDTO> response = restTemplate.postForEntity(BASE_URL + port + "/workout", newWorkoutDTO, WorkoutDTO.class);
        System.out.println(response);

        newWorkoutDTO = new WorkoutDTO();
        newWorkoutDTO.setUserId(1L);
        RunningWorkoutMetadata metadata1 = new RunningWorkoutMetadata();
        metadata1.setDistance(BigDecimal.TEN);
        newWorkoutDTO.setWorkoutMetaData(metadata1);
        response = restTemplate.postForEntity(BASE_URL + port + "/workout", newWorkoutDTO, WorkoutDTO.class);
        System.out.println(response);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    @Rollback
    void findList() {
        insertWorkout();
        ResponseEntity<String> response = restTemplate.getForEntity(BASE_URL + "/workout/list", String.class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        System.out.println(response);
    }
}
