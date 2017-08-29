package com.cy.dao.system;

import com.cy.entity.system.PermissionOperationRef;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zxj on 2017-08-29 10:32:27.
 */
@Repository
public interface PermissionOperationRefDAO {

    int insert(PermissionOperationRef entity);

    int batchInsert(List<PermissionOperationRef> list);

    int delete(Long id);

    int batchDelete(List<Long> list);

    int update(PermissionOperationRef entity);

    PermissionOperationRef getById(Long id);

    List<PermissionOperationRef> getByIdList(List<Long> list);

}
