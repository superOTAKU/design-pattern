package otaku.lottery.lottery.service;

import otaku.lottery.lottery.domain.entity.Award;

public interface AwardPoolService {

    /**
     *  抽奖
     * @param awardPoolId 奖池id
     *
     */
    public String drawAward(Long awardPoolId);

}
