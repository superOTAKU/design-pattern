package otaku.lottery.lottery.repository;

import otaku.lottery.lottery.domain.entity.Award;

import java.util.List;

public interface AwardRepository {
    List<Award> findAwardsByPoolId(Long awardPoolId);
}
