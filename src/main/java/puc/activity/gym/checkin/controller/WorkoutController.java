package puc.activity.gym.checkin.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import puc.activity.gym.checkin.dto.WorkoutDTO;
import puc.activity.gym.checkin.service.WorkoutService;

import java.util.List;

@RestController
@RequestMapping(value = "/workout", produces = MediaType.APPLICATION_JSON_VALUE)
public class WorkoutController {
    WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public WorkoutDTO insert(@RequestBody WorkoutDTO workoutDTO) {
        return workoutService.insert(workoutDTO);
    }

    @GetMapping(path = "/list")
    public List<WorkoutDTO> getList() {
        return workoutService.findAll();
    }
}
