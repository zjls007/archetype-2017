package com.cy.dao.system;

import com.cy.entity.system.PermissionInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zxj on 2017-08-29 10:32:27.
 */
@Repository
public interface PermissionInfoDAO {

    int insert(PermissionInfo entity);

    int batchInsert(List<PermissionInfo> list);

    int delete(Long id);

    int batchDelete(List<Long> list);

    int update(PermissionInfo entity);

    PermissionInfo getById(Long id);

    List<PermissionInfo> getByIdList(List<Long> list);

}
