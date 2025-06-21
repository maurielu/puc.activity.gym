package puc.activity.gym.checkin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import puc.activity.gym.checkin.model.RunningMetadata;

@Repository
public interface RunningMetadataRepository extends JpaRepository<RunningMetadata, Long> {
}
