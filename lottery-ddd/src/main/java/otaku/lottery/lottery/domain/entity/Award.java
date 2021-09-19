package otaku.lottery.lottery.domain.entity;

import otaku.lottery.common.Entity;

import java.util.Objects;

/**
 * 奖池中的一个奖品
 */
public class Award implements Entity {
    private Long id;
    private Long poolId;
    private String content;
    //奖品的个数
    private Integer count;
    private double chance;

    public Award() {}

    public Award(Long id, Long poolId, String content, double chance) {
        this.id = id;
        this.poolId = poolId;
        this.content = content;
        this.chance = chance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPoolId() {
        return poolId;
    }

    public void setPoolId(Long poolId) {
        this.poolId = poolId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getChance() {
        return chance;
    }

    public void setChance(double chance) {
        this.chance = chance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Award award = (Award) o;
        return Objects.equals(id, award.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Award{" +
                "id=" + id +
                ", poolId=" + poolId +
                ", content='" + content + '\'' +
                ", chance=" + chance +
                '}';
    }

}
