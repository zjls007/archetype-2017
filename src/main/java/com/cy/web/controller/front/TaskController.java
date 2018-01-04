package com.cy.web.controller.front;

import com.cy.common.Response;
import com.cy.entity.Task;
import com.cy.entity.system.UserInfo;
import com.cy.web.controller.front.base.LayerTableAdaptController;
import com.cy.web.dto.param.system.UserInfoFrontQueryDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by zxj on 2018/1/4.
 */
@Controller
@RequestMapping("task")
public class TaskController extends LayerTableAdaptController<Task, Task> {

    @Override
    protected void doEdit(Long id, ModelMap modelMap) {

    }

    @Override
    protected List<? extends Task> getData(Task queryDTO) {
        return null;
    }

    @Override
    public Response doSaveOrUpdate(Task task) {
        return null;
    }

    @Override
    protected void doDelete(List<Long> idList) {

    }


}
