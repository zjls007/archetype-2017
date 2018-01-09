package com.cy.dao;

import com.cy.entity.AttachmentRef;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zxj on 2018-01-08 16:33:37.
 */
@Repository
public interface AttachmentRefDAO {

    int insert(AttachmentRef entity);

    int batchInsert(List<AttachmentRef> list);

    int deleteById(Long id);

    int deleteByTaskId(Long id);

    int batchDelete(List<Long> list);

    int updateByIdSelective(AttachmentRef entity);

    int updateById(AttachmentRef entity);

    AttachmentRef getById(Long id);

    List<AttachmentRef> getByIdList(List<Long> list);

    List<AttachmentRef> list(AttachmentRef entity);

    List<AttachmentRef> listByRefIdAndRefType(@Param("refId") Long refId, @Param("refType") String refType);

}
