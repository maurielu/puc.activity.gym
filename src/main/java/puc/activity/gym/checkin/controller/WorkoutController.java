package puc.activity.gym.checkin.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import puc.activity.gym.checkin.model.Workout;
import puc.activity.gym.checkin.service.WorkoutService;

import java.util.List;

@RestController
@RequestMapping(value = "/workout", produces = MediaType.APPLICATION_JSON_VALUE)
public class WorkoutController {
    WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Workout insert(@RequestBody Workout workout) {
        return workoutService.insert(workout);
    }

    @GetMapping(path = "/list")
    public List<Workout> getList() {
        return workoutService.findAll();
    }
}
