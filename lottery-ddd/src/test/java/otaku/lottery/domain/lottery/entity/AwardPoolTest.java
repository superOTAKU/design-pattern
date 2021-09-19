package otaku.lottery.domain.lottery.entity;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import otaku.lottery.domain.lottery.repository.InMemoryAwardRepository;
import otaku.lottery.lottery.domain.entity.Award;
import otaku.lottery.lottery.domain.entity.AwardPool;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class AwardPoolTest {

    @AllArgsConstructor
    static class AwardResult {
        Award award;
        int count;
    }

    @Test
    public void drawAward() {
        AwardPool awardPool = new AwardPool(1L, "pool");
        InMemoryAwardRepository awardRepository = new InMemoryAwardRepository();
        awardPool.setAwardRepository(awardRepository);
        double totalChance = 0;
        for (int i = 0; i < 100; i++) {
            double chance = ThreadLocalRandom.current().nextDouble(0, 0.05);
            totalChance += chance;
            if (totalChance > 1) {
                break;
            }
            Award award = new Award((long)(i + 1), 1L, "award-" + i, chance);
            awardRepository.addAward(award);
        }
        //打印出每个奖品，奖品中奖概率，实际中奖概率
        Map<Long, AwardResult> resultMap = new HashMap<>();
        final int totalCount = 10000000;
        for (int i = 0; i < totalCount; i++) {
            Award award = awardPool.drawAward();
            if (award == null) {
                continue;
            }
            AwardResult awardResult = resultMap.computeIfAbsent(award.getId(), id -> new AwardResult(award, 0));
            awardResult.count++;
        }
        System.out.println("totalCount: " + totalCount);
        for (var result : resultMap.values()) {
            System.out.println("award " + result.award + ", count " + result.count + ", actual chance " + (result.count * 1.0 / totalCount));
        }
    }

}
