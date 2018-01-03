package com.cy.service;

import com.cy.entity.system.UserInfo;
import com.cy.web.dto.param.system.ModifyPwdDTO;
import com.cy.web.dto.param.system.RegistParamDTO;

import java.util.List;

/**
 * Created by zxj on 2017/2/25.
 */
public interface UserInfoService {

    void saveRefRoleInfo(Long userInfoId, List<Long> roleInfoIdList);

    void saveOrUpdate(UserInfo userInfo);

    /**
     * 注册
     * @param paramDTO
     * @return 用户id
     * @throws
     */
    UserInfo regist(RegistParamDTO paramDTO);

    /**
     * 变更用户锁定状态
     *
     * @param userInfoId {@link UserInfo#id}
     * @param currentUserId {@link UserInfo#id}
     * @param accountLocked {@link UserInfo#accountLocked}
     * @return
     * @throws
     */
    void changeLockState(Long userInfoId, Long currentUserId, Byte accountLocked);

    /**
     * 修改密码
     * @param dto
     * @param userId
     */
    void modifyPwd(ModifyPwdDTO dto, Long userId);

}
