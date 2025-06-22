package puc.activity.gym.checkin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import puc.activity.gym.checkin.model.StrengthSeries;

@Repository
public interface StrengthSeriesRepository extends JpaRepository<StrengthSeries, Long> {
}
