package otaku.lottery.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 系统中的用户，两种角色，admin可以设置奖池，普通用户只能参与抽奖
 */
@Data
@Entity
public class User {
    public static final String ROLE_USER = "user";
    public static final String ROLE_ADMIN = "admin";

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String role;
}
