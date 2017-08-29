package com.cy.dao.system;

import com.cy.entity.system.MenuInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zxj on 2017-08-29 10:32:27.
 */
@Repository
public interface MenuInfoDAO {

    int insert(MenuInfo entity);

    int batchInsert(List<MenuInfo> list);

    int delete(Long id);

    int batchDelete(List<Long> list);

    int update(MenuInfo entity);

    MenuInfo getById(Long id);

    List<MenuInfo> getByIdList(List<Long> list);

}
