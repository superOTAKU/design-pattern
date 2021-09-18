package otaku.lottery.lottery.repository;

import otaku.lottery.lottery.domain.entity.AwardPool;

public interface AwardPoolRepository {

    AwardPool findById(Long id);

}
