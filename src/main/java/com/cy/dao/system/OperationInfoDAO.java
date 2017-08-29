package com.cy.dao.system;

import com.cy.entity.system.OperationInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zxj on 2017-08-29 10:32:27.
 */
@Repository
public interface OperationInfoDAO {

    int insert(OperationInfo entity);

    int batchInsert(List<OperationInfo> list);

    int delete(Long id);

    int batchDelete(List<Long> list);

    int update(OperationInfo entity);

    OperationInfo getById(Long id);

    List<OperationInfo> getByIdList(List<Long> list);

}
