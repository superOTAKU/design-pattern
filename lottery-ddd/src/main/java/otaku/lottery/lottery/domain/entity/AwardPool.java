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

    private static final int LIST_SIZE = 10000;

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

    /**
     * 抽奖，主要的领域逻辑就是这里了
     * @return 奖品，如果没抽中，结果为null
     */
    public Award drawAward() {
        List<Award> awards = getAwards();
        //怎么抽奖？这里逻辑是创建10000个格子，按概率设置格子的值，概率多的格子多
        List<Integer> slots = new ArrayList<>(LIST_SIZE);
        int totalCount = 0;
        for (int i = 0; i < awards.size(); i++) {
            int count = (int)(awards.get(i).getChance() * LIST_SIZE);
            for (int j = 0; j < count && totalCount < LIST_SIZE; j++, totalCount++) {
                slots.add(i);
            }
        }
        //剩下的放-1
        for (int i = slots.size(); i < LIST_SIZE; i++) {
            slots.add(-1);
        }
        //打乱格子顺序
        Collections.shuffle(slots);
        //抽奖
        int randomIndex = ThreadLocalRandom.current().nextInt(0, LIST_SIZE);
        Integer awardIndex = slots.get(randomIndex);
        if (awardIndex == -1) {
            return null;
        }
        return awards.get(awardIndex);
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
