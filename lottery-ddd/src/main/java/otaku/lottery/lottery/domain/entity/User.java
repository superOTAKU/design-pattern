package otaku.lottery.lottery.domain.entity;

import otaku.lottery.common.Entity;

/**
 * 系统中的用户，两种角色，admin可以设置奖池，普通用户只能参与抽奖
 */
public class User implements Entity {
    public static final String ROLE_USER = "user";
    public static final String ROLE_ADMIN = "admin";

    private Long id;
    private String name;
    private String role;
}
