package com.ovft.configure.constant;

public class ConstantClassField {

    /**
     * redis  pc端token缓存过期时间   //一天
     */
    public static final long PC_CACHE_EXPIRATION_TIME = 24*60*60;

    /**
     * redis  单点登录功能key   single sign on   SSO
     */
    public static final String SINGLE_SIGN_ON = "SSO_ADMIN";

    /**
     * redis
     */
    public static final String ADMIN_INFO = "ADMIN_INFO";

    /**
     *  管理员状态  0-超级管理员
     */
    public static final Integer ROLE_SUPERADMIN_STATUS = 0;

    /**
     *  管理员状态  1-管理员
     */
    public static final Integer ROLE_ADMIN_STATUS = 1;

    /**
     *  管理员状态  2-教师
     */
    public static final Integer ROLE_TEACHER_STATUS = 2;


}
