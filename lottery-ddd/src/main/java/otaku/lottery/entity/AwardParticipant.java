package otaku.lottery.entity;

import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Data
@Entity
public class AwardParticipant {
    @EmbeddedId
    private AwardParticipantId awardParticipantId;
    //可参与抽奖的次数
    private Integer count;
}
