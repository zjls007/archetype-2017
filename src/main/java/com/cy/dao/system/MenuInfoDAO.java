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

    int insertHasId(MenuInfo entity);

    int batchInsert(List<MenuInfo> list);

    int delete(Long id);

    int deleteAll();

    int batchDelete(List<Long> list);

    int updateNoIf(MenuInfo entity);

    int update(MenuInfo entity);

    MenuInfo getById(Long id);

    List<MenuInfo> getByIdList(List<Long> list);

    List<MenuInfo> list();

    List<Long> getAllIdList();

}
