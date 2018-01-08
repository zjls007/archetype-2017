package com.cy.dao;

import com.cy.entity.Attachment;
import org.springframework.stereotype.Repository;


import java.util.List;

/**
 * Created by zxj on 2018-01-08 10:42:01.
 */
@Repository
public interface AttachmentDAO {

    int insert(Attachment entity);

    int batchInsert(List<Attachment> list);

    int deleteById(String id);

    int batchDelete(List<String> list);

    int updateByIdSelective(Attachment entity);

    int updateById(Attachment entity);

    Attachment getById(String id);

    List<Attachment> getByIdList(List<String> list);

    List<Attachment> list(Attachment entity);

}
