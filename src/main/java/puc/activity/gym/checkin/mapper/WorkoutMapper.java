package puc.activity.gym.checkin.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.factory.Mappers;
import puc.activity.gym.checkin.dto.WorkoutDTO;
import puc.activity.gym.checkin.model.Workout;

@Mapper(componentModel = ComponentModel.SPRING, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface WorkoutMapper {
    WorkoutMapper INSTANCE = Mappers.getMapper(WorkoutMapper.class);

    WorkoutDTO toDto(Workout workout);

    Workout toWorkoutEntity(WorkoutDTO workoutDTO);
}
