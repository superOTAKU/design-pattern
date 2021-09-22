package otaku.lottery.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 奖池中的一个奖品
 */
@Entity
@Data
public class Award {
    @Id
    @GeneratedValue
    private Long id;
    private Long poolId;
    private String content;
    private Integer count;
    private double chance;

}
