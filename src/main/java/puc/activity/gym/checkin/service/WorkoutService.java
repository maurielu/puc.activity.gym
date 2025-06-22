package puc.activity.gym.checkin.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import puc.activity.gym.checkin.dto.WorkoutDTO;
import puc.activity.gym.checkin.mapper.WorkoutMapper;
import puc.activity.gym.checkin.model.RunningMetadata;
import puc.activity.gym.checkin.model.StrengthMetadata;
import puc.activity.gym.checkin.model.StrengthSeries;
import puc.activity.gym.checkin.model.Workout;
import puc.activity.gym.checkin.repository.RunningMetadataRepository;
import puc.activity.gym.checkin.repository.WorkoutRepository;
import util.GymHelper;

import java.util.List;
import java.util.stream.Collectors;

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

        workout.setUserId(GymHelper.getOidcUserId());
        workout.setUsername(GymHelper.getOidcUsername());
        setBidirectionalRelations(workout);

        workoutRepository.save(workout);
        return workoutMapper.toDto(workout);
    }

    private void setBidirectionalRelations(Workout workout) {
        RunningMetadata runningMetadata = workout.getRunningMetadata();
        if (runningMetadata != null) {
            runningMetadata.setWorkout(workout);
        }

        StrengthMetadata strengthMetadata = workout.getStrengthMetadata();
        if (strengthMetadata != null) {
            strengthMetadata.setWorkout(workout);

            List<StrengthSeries> seriesList = strengthMetadata.getStrengthSeries();
            if (!seriesList.isEmpty()) {
                seriesList.stream().forEach(series -> series.setStrengthMetadata(strengthMetadata));
            }
        }
    }

    @Transactional
    public List<WorkoutDTO> findAll() {
        var workoutList = workoutRepository.findAll();
        List<WorkoutDTO> workoutDTOList = workoutList.stream()//
                .map(workoutMapper::toDto)//
                .collect(Collectors.toList());
        return workoutDTOList;
    }
}
