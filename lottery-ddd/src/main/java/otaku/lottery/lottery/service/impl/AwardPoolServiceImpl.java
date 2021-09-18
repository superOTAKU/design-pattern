package otaku.lottery.lottery.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import otaku.lottery.lottery.domain.entity.Award;
import otaku.lottery.lottery.domain.entity.AwardPool;
import otaku.lottery.lottery.repository.AwardPoolRepository;
import otaku.lottery.lottery.repository.AwardRepository;
import otaku.lottery.lottery.service.AwardPoolService;

@Slf4j
@Service
public class AwardPoolServiceImpl implements AwardPoolService {
    @Autowired
    private AwardPoolRepository awardPoolRepository;
    @Autowired
    private AwardRepository awardRepository;

    @Override
    public String drawAward(Long awardPoolId) {
        //TODO 实际上应该是用户抽奖
        AwardPool awardPool = awardPoolRepository.findById(awardPoolId);
        awardPool.setAwardRepository(awardRepository);
        Award award = awardPool.drawAward();
        log.info("award: {}", award);
        return award != null ? award.getContent() : null;
    }

}
