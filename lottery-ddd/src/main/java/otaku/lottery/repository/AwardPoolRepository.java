package otaku.lottery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import otaku.lottery.entity.AwardPool;

@Repository
public interface AwardPoolRepository extends JpaRepository<AwardPool, Long> {
}
