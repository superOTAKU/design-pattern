package otaku.lottery.service;

import otaku.lottery.dto.DrawResult;

public interface AwardPoolService {

    /**
     *  抽奖
     * @param participantId 奖池拥有者id
     * @param awardPoolId 奖池id
     *
     */
     DrawResult drawAward(Long participantId, Long awardPoolId);

    /**
     * 创建奖池
     * @param ownerId 奖池拥有者
     * @param name 奖池名称
     */
     Long createAwardPool(Long ownerId, String name);

}
