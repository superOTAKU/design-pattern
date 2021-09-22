package otaku.lottery.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import otaku.lottery.entity.AwardPool;
import otaku.lottery.repository.*;

@Component
public class AwardPoolAggregateFactory {
    @Autowired
    private AwardPoolRepository awardPoolRepository;
    @Autowired
    private AwardRepository awardRepository;
    @Autowired
    private AwardParticipantRepository awardParticipantRepository;
    @Autowired
    private DrawRecordRepository drawRecordRepository;
    @Autowired
    private UserRepository userRepository;

    public AwardPoolAggregate valueOf(Long awardPoolId) {
        AwardPoolAggregate aggregate = new AwardPoolAggregate();
        aggregate.id = awardPoolId;
        aggregate.awardPoolRepository = awardPoolRepository;
        aggregate.awardRepository = awardRepository;
        aggregate.awardParticipantRepository = awardParticipantRepository;
        aggregate.drawRecordRepository = drawRecordRepository;
        aggregate.userRepository = userRepository;
        return aggregate;
    }

    public AwardPoolAggregate newAwardPool(Long ownerId, String name) {
        AwardPool awardPool = new AwardPool();
        awardPool.setOwnerId(ownerId);
        awardPool.setName(name);
        awardPoolRepository.save(awardPool);
        return valueOf(awardPool.getId());
    }

}
