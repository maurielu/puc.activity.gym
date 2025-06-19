package puc.activity.gym.checkin.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import puc.activity.gym.checkin.model.Workout;
import puc.activity.gym.checkin.repository.WorkoutRepository;

import java.util.List;

@Service
public class WorkoutService {
    private WorkoutRepository workoutRepository;

    public WorkoutService(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    @Transactional
    public Workout insert(@Validated Workout workout) {
        return workoutRepository.save(workout);
    }

    @Transactional
    public List<Workout> findAll() {
        return workoutRepository.findAll();
    }
}
