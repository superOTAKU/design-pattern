package otaku.lottery.lottery.domain.entity;

import otaku.lottery.common.Entity;
import otaku.lottery.lottery.repository.AwardRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

/**
 * awardPool 是一个entity，有可能需要设置多个奖池，每个奖池是独立的
 */
public class AwardPool implements Entity {
    //外部确保id唯一
    private Long id;
    //奖池名称，用于展示
    private String name;
    private AwardRepository awardRepository;

    public AwardPool() {}

    public AwardPool(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * 查找奖池中的Award
     */
    public List<Award> getAwards() {
        return awardRepository.findAwardsByPoolId(id);
    }

    private static class Range {
        //左包含
        private double start;
        //右不包含
        private double end;

        boolean inRange(double num) {
            return num >= start && num < end;
        }
    }

    /**
     * 抽奖，主要的领域逻辑就是这里了
     * @return 奖品，如果没抽中，结果为null
     */
    public Award drawAward() {
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
        if (i < awards.size()) {
            return awards.get(i);
        }
        return null;
    }

    //依赖注入进来
    public void setAwardRepository(AwardRepository awardRepository) {
        this.awardRepository = awardRepository;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AwardPool awardPool = (AwardPool) o;
        return Objects.equals(id, awardPool.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "AwardPool{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
