package com.cy.web.controller.front;

import com.cy.common.Response;
import com.cy.dao.TaskDAO;
import com.cy.entity.Task;
import com.cy.service.TaskService;
import com.cy.web.controller.front.base.LayerTableAdaptController;
import com.cy.web.dto.param.system.TaskSaveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by zxj on 2018/1/4.
 */
@Controller
@RequestMapping("task")
public class TaskController extends LayerTableAdaptController<Task, Task> {

    @Autowired
    private TaskDAO taskDAO;

    @Autowired
    private TaskService taskService;

    @Override
    public String getModelNameCN() {
        return "任务";
    }

    @Override
    protected Task getModel(Long id) {
        return taskDAO.getById(id);
    }

    @Override
    protected List<? extends Task> getData(Task queryDTO) {
        return null;
    }

    @Override
    public Response doSaveOrUpdate(Task task) {
        return new Response();
    }

    @Override
    protected void doDelete(List<Long> idList) {

    }

    @RequestMapping("saveOrUpdateDTO")
    @ResponseBody
    public Response saveOrUpdate(TaskSaveDTO dto) {
        saveOrUpdatePerm(dto.getTask().getId());
        taskService.saveOrUpdate(dto);
        return new Response();
    }

}
