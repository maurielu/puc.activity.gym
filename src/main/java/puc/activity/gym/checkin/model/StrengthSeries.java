package puc.activity.gym.checkin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class StrengthSeries extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long strengthSeriesId;

    @NotBlank
    String exerciseName;

    @PositiveOrZero
    @NotNull
    int reps;

    @ManyToOne
    @JoinColumn(name = "strengthId")
    @ToString.Exclude
    @JsonIgnore
    private StrengthMetadata strengthMetadata;
}
