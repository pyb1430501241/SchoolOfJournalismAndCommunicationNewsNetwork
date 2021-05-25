package com.pdsu.sojacnn.controller;

import com.pdsu.sojacnn.bean.NewsAccount;
import com.pdsu.sojacnn.bean.NewsAccountRole;
import com.pdsu.sojacnn.exception.account.AccountNotLoginException;
import com.pdsu.sojacnn.exception.account.PermissionsException;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.Contract;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * 继承此类即代表需要权限校验
 * @author 半梦
 * @create 2021-05-21 18:57
 */
@SuppressWarnings("all")
@Log4j2
public abstract class AuthenticationController implements AbstractController {

    static final int TOURIST = 1;

    static final int BASIC_PERSONNEL = 2;

    static final int ADMINISTRATOR = 3;

    static final int SUPER_ADMIN = 4;

    /**
     * 判断用户是否登录, 如未登录抛出异常
     */
    @Contract("null -> fail")
    void loginOrNotLogin(@Nullable NewsAccount user) throws AccountNotLoginException {
        throw new AccountNotLoginException();
    }

    /**
     * 最低权限者, 只允许查看
     */
    boolean isTourist(@NonNull NewsAccountRole newsAccountRole) {
        return newsAccountRole.getRoleId() >= TOURIST;
    }

    /**
     * 普通权限者, 允许发布、修改、删除文章
     */
    boolean isBasicPersonnel(@NonNull NewsAccountRole newsAccountRole) {
        return newsAccountRole.getRoleId() >= BASIC_PERSONNEL;
    }

    /**
     * 管理员, 允许置顶文章、更换轮询文章、查看更新日志、添加老师
     */
    boolean isAdministrator(@NonNull NewsAccountRole newsAccountRole) {
        return newsAccountRole.getRoleId() >= ADMINISTRATOR;
    }

    /**
     * 超级管理员, 允许更新管理权限、分配管理员权限
     */
    boolean isSuperAdmin(@NonNull NewsAccountRole newsAccountRole) {
        return newsAccountRole.getRoleId() == SUPER_ADMIN;
    }

    /**
     * 权限不足时，抛出异常
     * @param newsAccountRole 用户权限
     * @param level 所需权限等级
     * @throws PermissionsException 权限不足
     */
    void authorityJudgment(NewsAccountRole newsAccountRole, Integer level) throws PermissionsException {
        switch (level) {
            case TOURIST:
                if (!isTourist(newsAccountRole)) {
                    throw new PermissionsException();
                }
                break;
            case BASIC_PERSONNEL:
                if (!isBasicPersonnel(newsAccountRole)) {
                    throw new PermissionsException();
                }
                break;
            case ADMINISTRATOR:
                if (!isAdministrator(newsAccountRole)) {
                    throw new PermissionsException();
                }
                break;
            case SUPER_ADMIN:
                if(!isSuperAdmin(newsAccountRole)) {
                    throw new PermissionsException();
                }
                break;
            default:
                throw new PermissionsException();
        }
        log.debug("用户: " + newsAccountRole + "通过权限校验.");
    }

}