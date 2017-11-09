package com.cy.dao.system;

import com.cy.entity.system.RoleInfo;
import com.cy.web.dto.param.system.RoleInfoParamDTO;
import com.cy.web.dto.result.system.RoleInfoListResultDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * Created by zxj on 2017-08-29 10:32:27.
 */
@Repository
public interface RoleInfoDAO {

    int insert(RoleInfo entity);

    int batchInsert(List<RoleInfo> list);

    int delete(Long id);

    int batchDelete(List<Long> list);

    int update(RoleInfo entity);

    RoleInfo getById(Long id);

    List<RoleInfo> getByIdList(List<Long> list);

    RoleInfo getByCode(String code);

    List<RoleInfo> list(RoleInfo queryDTO);

    List<RoleInfoListResultDTO> listDTO(RoleInfoParamDTO queryDTO);

    Set<String> getRoleCodeList(Long userInfoId);

    Set<String> getPermByUser(Long userInfoId);

}
