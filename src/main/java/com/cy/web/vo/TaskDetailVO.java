package com.cy.web.vo;

import com.cy.entity.Task;
import com.cy.web.dto.result.ImgResultDTO;

import java.util.List;

/**
 * Created by zxj on 2018/1/9.
 */
public class TaskDetailVO extends Task {

    private static final long serialVersionUID = 2030792575336539855L;

    private List<ImgResultDTO> imgList;

    public List<ImgResultDTO> getImgList() {
        return imgList;
    }

    public void setImgList(List<ImgResultDTO> imgList) {
        this.imgList = imgList;
    }

}
