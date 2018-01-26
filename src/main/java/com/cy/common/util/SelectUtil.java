package com.cy.common.util;

import com.cy.entity.system.enums.TaskDifficult;
import com.cy.entity.system.enums.TaskState;
import com.cy.entity.system.enums.TaskType;
import com.cy.web.vo.SelectOptionVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zxj on 2018/1/19.
 */
public class SelectUtil {

    public static Map<String, List<SelectOptionVO>> selectMap = new HashMap<String, List<SelectOptionVO>>();

    public static final String TASK_DIFFICULT = "task.difficult";
    public static final String TASK_STATE = "task.state";
    public static final String TASK_TYPE= "task.type";

    static {
        selectMap.put(TASK_DIFFICULT, TaskDifficult.getSelectList());
        selectMap.put(TASK_STATE, TaskState.getSelectList());
        selectMap.put(TASK_TYPE, TaskType.getSelectList());
    }

}
