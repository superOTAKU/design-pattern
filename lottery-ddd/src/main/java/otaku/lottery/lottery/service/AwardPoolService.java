package otaku.lottery.lottery.service;

public interface AwardPoolService {

    /**
     *  抽奖
     * @param awardPoolId 奖池id
     *
     */
     String drawAward(Long awardPoolId);

    /**
     * 创建奖池
     * @param userId 奖池拥有者
     * @param name 奖池名称
     */
     Long createAwardPool(Long userId, String name);

}
