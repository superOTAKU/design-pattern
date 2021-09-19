package otaku.lottery.lottery.repository;

import otaku.lottery.lottery.domain.entity.AwardPool;
import otaku.lottery.lottery.domain.entity.User;

import java.util.List;

public interface AwardPoolRepository {

    /**
     * 根据奖池id获取奖池
     * @param id 奖池id
     */
    AwardPool findById(Long id);

    /**
     * 根据奖池持有者id查找参与抽奖人员
     * @param ownerId 奖池拥有者
     */
    List<User> findParticipantsByOwnerId(Long ownerId);



}
