package com.cy.web.dto.param.system;

import com.cy.common.annotation.ParamValid;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * Created by zxj on 2018/2/3.
 */
@ParamValid
public class TaskNoteSaveDTO implements Serializable {
    private static final long serialVersionUID = 6004601730458034326L;

    @NotNull(message = "参数错误!")
    private Long taskId;

    private List<TaskNoteItemDTO> noteList;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public List<TaskNoteItemDTO> getNoteList() {
        return noteList;
    }

    public void setNoteList(List<TaskNoteItemDTO> noteList) {
        this.noteList = noteList;
    }
}
