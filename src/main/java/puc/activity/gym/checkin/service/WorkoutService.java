package puc.activity.gym.checkin.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import puc.activity.gym.checkin.dto.WorkoutDTO;
import puc.activity.gym.checkin.mapper.WorkoutMapper;
import puc.activity.gym.checkin.model.Workout;
import puc.activity.gym.checkin.repository.WorkoutRepository;

import java.util.List;

@Service
public class WorkoutService {
    private WorkoutRepository workoutRepository;
    private WorkoutMapper workoutMapper;

    public WorkoutService(WorkoutRepository workoutRepository, WorkoutMapper workoutMapper) {
        this.workoutRepository = workoutRepository;
        this.workoutMapper = workoutMapper;
    }

    @Transactional
    public WorkoutDTO insert(@Validated WorkoutDTO workoutDTO) {
        Workout workout = workoutMapper.toWorkoutEntity(workoutDTO);
        workoutRepository.save(workout);


        return workoutMapper.toDto(workout);
    }

    @Transactional
    public List<Workout> findAll() {
        return workoutRepository.findAll();
    }
}
