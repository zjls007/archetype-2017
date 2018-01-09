package com.cy.web.controller.front;

import com.cy.common.Response;
import com.cy.dao.TaskDAO;
import com.cy.entity.AttachmentRef;
import com.cy.entity.Task;
import com.cy.entity.TaskUser;
import com.cy.service.TaskService;
import com.cy.web.controller.front.base.LayerTableAdaptController;
import com.cy.web.dto.param.system.TaskSaveDTO;
import com.cy.web.dto.result.ImgResultDTO;
import com.cy.web.dto.result.TaskResultDTO;
import com.cy.web.vo.TaskDetailVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
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
    protected Object getModel(Long id, ModelMap modelMap) {
        TaskResultDTO taskResultDTO = taskService.get(id);
        TaskDetailVO vo = new TaskDetailVO();
        BeanUtils.copyProperties(taskResultDTO.getTask(), vo);
        vo.setImgList(new ArrayList<ImgResultDTO>());
        List<AttachmentRef> imgList = taskResultDTO.getImgList();
        for (AttachmentRef item : imgList) {
            ImgResultDTO dto = new ImgResultDTO();
            dto.setId(item.getFileId());
            vo.getImgList().add(dto);
        }
        return vo;
    }

    @Override
    protected List<? extends Task> getData(Task queryDTO) {
        return taskDAO.list(queryDTO);
    }

    @Override
    public Response doSaveOrUpdate(Task task) {
        return new Response();
    }

    @Override
    protected void doDelete(List<Long> idList) {
        taskService.batchDel(idList);
    }

    @RequestMapping("saveOrUpdateDTO")
    @ResponseBody
    public Response saveOrUpdate(TaskSaveDTO dto) {
        saveOrUpdatePerm(dto.getTask().getId());
        taskService.saveOrUpdate(dto, getCurrentUser());
        return new Response();
    }

}
