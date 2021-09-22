package otaku.lottery.domain;

import otaku.lottery.dto.DrawResult;
import otaku.lottery.entity.Award;
import otaku.lottery.entity.AwardParticipant;
import otaku.lottery.entity.AwardParticipantId;
import otaku.lottery.entity.DrawRecord;
import otaku.lottery.repository.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 奖池，聚合根
 */
public class AwardPoolAggregate {
    Long id;
    AwardPoolRepository awardPoolRepository;
    AwardRepository awardRepository;
    AwardParticipantRepository awardParticipantRepository;
    DrawRecordRepository drawRecordRepository;
    UserRepository userRepository;
    volatile List<Award> awards;
    final Map<Long, AwardParticipant> participantMap = new ConcurrentHashMap<>();

    private static class Range {
        //左包含
        private double start;
        //右不包含
        private double end;

        boolean inRange(double num) {
            return num >= start && num < end;
        }
    }

    public List<Award> getAwards() {
        if (awards == null) {
            synchronized (this) {
                if (awards == null) {
                    awards = awardRepository.findByPoolId(id);
                }
            }
        }
        return awards;
    }

    /**
     * 抽奖：
     *  1. 以缓存数据为准，先写缓存，再更新数据库(write behind)
     *  2. 允许对同一个奖池的并发抽奖
     *  3. 对内存中的数据操作要做对冲，如果数据库操作异常自行回滚(不是数据库的回滚)
     */
    public DrawResult drawAward(Long participantId) {
        AwardParticipant participant = participantMap.get(participantId);
        if (participant == null) {
            AwardParticipantId awardParticipantId = new AwardParticipantId();
            awardParticipantId.setAwardPoolId(id);
            awardParticipantId.setParticipantId(participantId);
            Optional<AwardParticipant> participantOpt = awardParticipantRepository.findById(awardParticipantId);
            if (participantOpt.isEmpty()) {
                throw new IllegalArgumentException(String.format("participant %s not exists!", participantId));
            }
            participantMap.putIfAbsent(participantId, participantOpt.get());
        }
        participant = participantMap.get(participantId);
        if (participant.getCount() == 0) {
            throw new RuntimeException("you don't have chance to draw!");
        }
        synchronized (participantMap) {
            participant = participantMap.get(participantId);
            if (participant.getCount() > 0) {
                participant.setCount(participant.getCount() - 1);
            } else {
                throw new RuntimeException("you don't have chance to draw!");
            }
        }
        List<Award> awards = new ArrayList<>(getAwards());
        Double totalChance = awards.stream().map(Award::getChance).reduce(0D, Double::sum);
        if (totalChance > 1) {
            throw new IllegalStateException("awards in this awardPool is illegal!");
        }
        //改算法，每个award对应0到1中的一个数据范围，范围根据自身概率确定
        Collections.shuffle(awards);
        double total = 0;
        List<Range> ranges = new ArrayList<>();
        for (var award : awards) {
            Range range = new Range();
            range.start = total;
            range.end = total + award.getChance();
            total += award.getChance();
            ranges.add(range);
        }
        double result = ThreadLocalRandom.current().nextDouble(0, 1);
        int i = 0;
        for (var range : ranges) {
            if (range.inRange(result)) {
                break;
            }
            i++;
        }
        boolean countModified = false;
        Award award = null;
        DrawResult drawResult = new DrawResult();
        try {
            DrawRecord drawRecord = new DrawRecord();
            drawRecord.setDrawerId(participantId);
            drawRecord.setAwardPoolId(id);
            if (i < awards.size()) {
                award = awards.get(i);
                synchronized (this) {
                    if (award.getCount() > 0) {
                        award.setCount(award.getCount() - 1);
                        countModified = true;
                        awardRepository.save(award);
                    }
                }
            }
            drawRecord.setAward(award == null ? "" : award.getContent());
            drawResult.setRecordId(drawRecord.getId());
            drawResult.setAward(drawRecord.getAward());
            drawRecordRepository.save(drawRecord);
            awardParticipantRepository.save(participant);
        } catch (Exception e) {
            synchronized (participantMap) {
                participant.setCount(participant.getCount() + 1);
            }
            if (countModified) {
                synchronized (this) {
                    award.setCount(award.getCount() + 1);
                }
            }
            if (e instanceof RuntimeException) {
                throw (RuntimeException)e;
            } else {
                throw new RuntimeException(e);
            }
        }
        return drawResult;
    }

    public Long getId() {
        return id;
    }

}
