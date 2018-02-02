package com.cy.web.controller.front;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cy.common.Response;
import com.cy.common.util.UserNameUtil;
import com.cy.dao.AttachmentDAO;
import com.cy.dao.TaskDAO;
import com.cy.dao.system.UserInfoDAO;
import com.cy.entity.Attachment;
import com.cy.entity.Task;
import com.cy.entity.TaskUser;
import com.cy.entity.system.UserInfo;
import com.cy.entity.system.enums.AttachmentRefFileType;
import com.cy.entity.system.enums.TaskState;
import com.cy.entity.system.enums.TaskType;
import com.cy.service.TaskService;
import com.cy.web.controller.front.base.LayerTableAdaptController;
import com.cy.web.dto.param.system.TaskQueryDTO;
import com.cy.web.dto.param.system.TaskSaveDTO;
import com.cy.web.dto.result.TaskResultDTO;
import com.cy.web.vo.ImgResultVO;
import com.cy.web.vo.Select2ItemVO;
import com.cy.web.vo.TaskDetailVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private AttachmentDAO attachmentDAO;

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
            select2ItemVO.setText(UserNameUtil.getUserName(userInfo));
            userList.add(select2ItemVO);
        }
        vo.setAttachmentList(taskResultDTO.getAttachmentList());
        vo.setShowBeginBtn(beginBtn(taskResultDTO));
        vo.setTaskNoteDTOList(taskService.initNote(vo.getId(), vo.getDueDate(), vo.getCreateUserId().longValue() == getCurrentUserId()));
        return vo;
    }

    private boolean beginBtn(TaskResultDTO dto) {
        Task task = dto.getTask();
        List<TaskUser> taskUserList = dto.getTaskUserList();
        if (TaskType.ASSIGN.getCode().equals(task.getType())
                && TaskState.TAKE.getCode().equals(task.getState())
                && con(taskUserList)) {
            return true;
        }
        return false;
    }

    private boolean con(List<TaskUser> taskUserList) {
        for (TaskUser item : taskUserList) {
            if (item.getUserId().longValue() == getCurrentUserId()) {
                return true;
            }
        }
        return false;
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
        taskService.batchDel(idList, getCurrentUserId());
    }

    @RequestMapping("saveOrUpdateDTO")
    @ResponseBody
    public Response saveOrUpdate(TaskSaveDTO dto) {
        saveOrUpdatePerm(dto.getTask().getId());
        taskService.saveOrUpdate(dto, getCurrentUser());
        return new Response();
    }

    /**
     * 首页-待处理任务列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("pending")
    @ResponseBody
    public Object pending(@RequestParam("page") Integer pageNum, @RequestParam("limit")Integer pageSize) {
        Map<String, Object> map = new HashMap<String, Object>();
        PageHelper.startPage(pageNum, pageSize);
        List list = taskDAO.getPendingTask(getCurrentUserId());
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", ((Page) list).getTotal());
        map.put("data", list);
        doDataOther(map);
        return map;
    }

    @RequestMapping("photos")
    @ResponseBody
    public Response photos(Long taskId, Integer index) {
        JSONObject setting = new JSONObject();
        // 动画效果: 参数 0-6
        setting.put("anim", 5);
        JSONObject photos = new JSONObject();
        setting.put("photos", photos);

        photos.put("title", "");
        photos.put("id", "1");
        photos.put("start", index);

        JSONArray datas = new JSONArray();
        List<Attachment> attachments = attachmentDAO.listByTask(taskId, AttachmentRefFileType.IMG.getCode());
        for (Attachment item : attachments) {
            JSONObject data = new JSONObject();
            data.put("alt", "");
            data.put("pid", item.getId());
            data.put("src", String.format("img/%s/0", item.getId()));
            data.put("thumb", String.format("img/%s/1", item.getId()));
            datas.add(data);
        }
        photos.put("data", datas);
        return new Response(setting);
    }

    @RequestMapping({"begin/{navigation}/{id}"})
    public String operBegin(@PathVariable String navigation, @PathVariable Long id, ModelMap modelMap) {
        taskService.begin(id, getCurrentUserId());
        view(navigation, id, modelMap);
        return genPath("view");
    }

}
