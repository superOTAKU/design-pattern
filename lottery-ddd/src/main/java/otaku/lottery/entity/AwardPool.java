package otaku.lottery.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class AwardPool {
    @Id
    @GeneratedValue
    private Long id;
    private Long ownerId;
    private String name;

}
