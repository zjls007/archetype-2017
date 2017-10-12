package com.cy.dao.system;

import com.cy.entity.system.RolePermissionRef;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zxj on 2017-08-29 10:32:27.
 */
@Repository
public interface RolePermissionRefDAO {

    int insert(RolePermissionRef entity);

    int batchInsert(List<RolePermissionRef> list);

    int delete(Long id);

    int batchDelete(List<Long> list);

    int update(RolePermissionRef entity);

    RolePermissionRef getById(Long id);

    List<RolePermissionRef> getByIdList(List<Long> list);

    int deleteByRoleInfoId(@Param("roleInfoId") Long roleInfoId, @Param("type") String type);

    List<Long> getMenuByRoleInfoId(Long roleInfoId);

}
