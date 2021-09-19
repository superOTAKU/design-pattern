package otaku.lottery.lottery.domain.entity;

import otaku.lottery.common.Entity;

/**
 * 抽奖记录
 */
public class DrawRecord implements Entity {
    private Long id;
    //奖池id
    private Long awardPoolId;
    //抽奖人
    private Long drawerId;
    //获得的奖品，没中奖则为空
    private String award;
}
