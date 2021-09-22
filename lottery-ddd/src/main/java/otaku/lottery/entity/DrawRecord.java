package otaku.lottery.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 抽奖记录
 */
@Data
@Entity
public class DrawRecord {
    @Id
    @GeneratedValue
    private Long id;
    //奖池id
    private Long awardPoolId;
    //抽奖人
    private Long drawerId;
    //获得的奖品，没中奖则为空
    private String award;
}
