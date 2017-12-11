package com.cy.dao.system;

import com.cy.entity.system.UserInfo;
import com.cy.web.dto.param.system.UserInfoQueryDTO;
import com.cy.web.dto.result.system.UserInfoListResultDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zxj on 2017-12-06 14:19:19.
 */
@Repository
public interface UserInfoDAO {

    int insert(UserInfo entity);

    int batchInsert(List<UserInfo> list);

    int deleteById(Long id);

    int batchDelete(List<Long> list);

    int updateByIdSelective(UserInfo entity);

    int updateById(UserInfo entity);

    int updateLastLoginTime(Long id);

    UserInfo getById(Long id);

    List<UserInfo> getByIdList(List<Long> list);

    List<UserInfo> list(UserInfo entity);

    UserInfo getByUserName(String userName);

    int uniUserName(@Param("userName") String userName, @Param("id") Long id);

    UserInfo getByEmail(String email);

    UserInfo getByMobilePhoneNumber(String mobilePhoneNumber);

    List<UserInfoListResultDTO> listUserInfo();

    List<UserInfo> listByDTO(UserInfoQueryDTO queryDTO);

    List<Long> getMenuIdList(Long userInfoId);

    int updateAccountLocked(@Param("id") Long id, @Param("accountLocked") Byte accountLocked);

}
