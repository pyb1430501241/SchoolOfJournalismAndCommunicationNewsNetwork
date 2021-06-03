package com.pdsu.sojacnn.controller;

import com.pdsu.sojacnn.bean.NewsAccount;
import com.pdsu.sojacnn.bean.NewsAccountRole;
import com.pdsu.sojacnn.exception.account.AccountNotLoginException;
import com.pdsu.sojacnn.exception.account.PermissionsException;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.Contract;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.function.Function;
import java.util.function.Predicate;

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

    private static final PermissionsException PERMISSIONS_EXCEPTION = new PermissionsException();

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
    void isTourist(@NonNull NewsAccountRole newsAccountRole) throws PermissionsException {
        authorityJudgment(newsAccountRole, TOURIST);
    }

    /**
     * 普通权限者, 允许发布、修改、删除文章
     */
    void isBasicPersonnel(@NonNull NewsAccountRole newsAccountRole) throws PermissionsException {
        authorityJudgment(newsAccountRole, BASIC_PERSONNEL);
    }

    /**
     * 管理员, 允许置顶文章、更换轮询文章、查看更新日志、添加老师
     */
    void isAdministrator(@NonNull NewsAccountRole newsAccountRole) throws PermissionsException {
        authorityJudgment(newsAccountRole, ADMINISTRATOR);
    }

    /**
     * 超级管理员, 允许更新管理权限、分配管理员权限
     */
    void isSuperAdmin(@NonNull NewsAccountRole newsAccountRole) throws PermissionsException {
        authorityJudgment(newsAccountRole, SUPER_ADMIN);
    }

    void authorityJudgment(Predicate<NewsAccountRole> predicate, NewsAccountRole accountRole) throws PermissionsException{
        if(!predicate.test(accountRole)) {
            throw PERMISSIONS_EXCEPTION;
        }
    }

    /**
     * 权限不足时，抛出异常
     * @param newsAccountRole 用户权限
     * @param level 所需权限等级
     * @throws PermissionsException 权限不足r
     */
    void authorityJudgment(NewsAccountRole newsAccountRole, Integer level) throws PermissionsException {
        authorityJudgment(e -> e.getRoleId() >= level, newsAccountRole);
//        switch (level) {
//            case TOURIST:
//                if (!isTourist(newsAccountRole)) {
//                    throw PERMISSIONS_EXCEPTION;
//                }
//                break;
//            case BASIC_PERSONNEL:
//                if (!isBasicPersonnel(newsAccountRole)) {
//                    throw PERMISSIONS_EXCEPTION;
//                }
//                break;
//            case ADMINISTRATOR:
//                if (!isAdministrator(newsAccountRole)) {
//                    throw PERMISSIONS_EXCEPTION;
//                }
//                break;
//            case SUPER_ADMIN:
//                if(!isSuperAdmin(newsAccountRole)) {
//                    throw PERMISSIONS_EXCEPTION;
//                }
//                break;
//            default:
//                throw PERMISSIONS_EXCEPTION;
//        }
        log.info("用户: " + newsAccountRole + "通过权限校验.");
    }

}