package otaku.lottery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import otaku.lottery.entity.AwardParticipant;
import otaku.lottery.entity.AwardParticipantId;

@Repository
public interface AwardParticipantRepository extends JpaRepository<AwardParticipant, AwardParticipantId> {
}
