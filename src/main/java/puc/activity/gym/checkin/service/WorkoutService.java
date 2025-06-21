package puc.activity.gym.checkin.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import puc.activity.gym.checkin.dto.WorkoutDTO;
import puc.activity.gym.checkin.mapper.WorkoutMapper;
import puc.activity.gym.checkin.model.RunningMetadata;
import puc.activity.gym.checkin.model.Workout;
import puc.activity.gym.checkin.repository.RunningMetadataRepository;
import puc.activity.gym.checkin.repository.WorkoutRepository;

import java.util.List;

@Service
public class WorkoutService {
    WorkoutRepository workoutRepository;
    RunningMetadataRepository runningMetadataRepository;
    WorkoutMapper workoutMapper;

    public WorkoutService(WorkoutRepository workoutRepository, RunningMetadataRepository runningMetadataRepository, WorkoutMapper workoutMapper) {
        this.workoutRepository = workoutRepository;
        this.runningMetadataRepository = runningMetadataRepository;
        this.workoutMapper = workoutMapper;
    }

    @Transactional
    public WorkoutDTO insert(@Validated WorkoutDTO workoutDTO) {
        Workout workout = workoutMapper.toWorkoutEntity(workoutDTO);
        RunningMetadata runningMetadata = workout.getRunningMetadata();
        if (runningMetadata != null) {
            runningMetadata.setWorkout(workout);
        }
        workoutRepository.save(workout);
        return workoutMapper.toDto(workout);
    }

    @Transactional
    public List<Workout> findAll() {
        return workoutRepository.findAll();
    }
}
