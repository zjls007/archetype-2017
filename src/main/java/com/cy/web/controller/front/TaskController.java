package com.cy.web.controller.front;

import com.cy.common.Response;
import com.cy.dao.TaskDAO;
import com.cy.dao.system.UserInfoDAO;
import com.cy.entity.Attachment;
import com.cy.entity.Task;
import com.cy.entity.TaskUser;
import com.cy.entity.system.UserInfo;
import com.cy.service.TaskService;
import com.cy.web.controller.front.base.LayerTableAdaptController;
import com.cy.web.dto.param.system.TaskQueryDTO;
import com.cy.web.dto.param.system.TaskSaveDTO;
import com.cy.web.dto.result.TaskResultDTO;
import com.cy.web.vo.ImgResultVO;
import com.cy.web.vo.Select2ItemVO;
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
public class TaskController extends LayerTableAdaptController<Task, TaskQueryDTO> {

    @Autowired
    private UserInfoDAO userInfoDAO;

    @Autowired
    private TaskDAO taskDAO;

    @Autowired
    private TaskService taskService;

    @Override
    public String getModelNameCN() {
        return "任务";
    }

    @Override
    public Object getModel(Long id, ModelMap modelMap) {
        TaskResultDTO taskResultDTO = taskService.get(id);
        TaskDetailVO vo = new TaskDetailVO();
        BeanUtils.copyProperties(taskResultDTO.getTask(), vo);
        vo.setImgList(new ArrayList<ImgResultVO>());
        List<Attachment> imgList = taskResultDTO.getImgList();
        for (Attachment item : imgList) {
            ImgResultVO dto = new ImgResultVO();
            dto.setId(item.getId());
            vo.getImgList().add(dto);
        }

        List<Select2ItemVO> userList = new ArrayList<Select2ItemVO>();
        vo.setUserList(userList);
        for (TaskUser item : taskResultDTO.getTaskUserList()) {
            Select2ItemVO select2ItemVO = new Select2ItemVO();
            UserInfo userInfo = userInfoDAO.getById(item.getUserId());
            select2ItemVO.setId(Long.toString(item.getUserId()));
            select2ItemVO.setText(userInfo.getUserName());
            userList.add(select2ItemVO);
        }
        vo.setAttachmentList(taskResultDTO.getAttachmentList());
        return vo;
    }

    @Override
    protected List<? extends Task> getData(TaskQueryDTO queryDTO) {
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
