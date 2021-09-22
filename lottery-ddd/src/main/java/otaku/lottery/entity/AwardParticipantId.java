package otaku.lottery.entity;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class AwardParticipantId implements Serializable {
    private Long participantId;
    private Long awardPoolId;
}
