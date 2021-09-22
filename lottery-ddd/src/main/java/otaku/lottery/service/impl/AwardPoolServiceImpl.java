package otaku.lottery.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import otaku.lottery.domain.AwardPoolAggregate;
import otaku.lottery.domain.AwardPoolAggregateFactory;
import otaku.lottery.dto.DrawResult;
import otaku.lottery.service.AwardPoolService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AwardPoolServiceImpl implements AwardPoolService {
    @Autowired
    private AwardPoolAggregateFactory aggregateFactory;
    private final Map<Long, AwardPoolAggregate> cache = new ConcurrentHashMap<>();

    @Transactional
    @Override
    public DrawResult drawAward(Long participantId, Long awardPoolId) {
        AwardPoolAggregate aggregate = cache.computeIfAbsent(awardPoolId, id -> aggregateFactory.valueOf(awardPoolId));
        return aggregate.drawAward(participantId);
    }

    @Transactional
    @Override
    public Long createAwardPool(Long ownerId, String name) {
        AwardPoolAggregate aggregate = aggregateFactory.newAwardPool(ownerId, name);
        cache.putIfAbsent(aggregate.getId(), aggregate);
        return aggregate.getId();
    }
}
