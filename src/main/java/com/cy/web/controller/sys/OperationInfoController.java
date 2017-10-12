package com.cy.web.controller.sys;

import com.cy.common.Response;
import com.cy.dao.system.OperationInfoDAO;
import com.cy.entity.system.OperationInfo;
import com.cy.web.controller.sys.base.DataGridAdaptController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by zxj on 2017/10/12.
 */
@Controller
@RequestMapping("operationInfo")
public class OperationInfoController extends DataGridAdaptController<OperationInfo, OperationInfo> {

    @Autowired
    private OperationInfoDAO operationInfoDAO;

    @Override
    protected List<OperationInfo> getData(OperationInfo queryDTO) {
        return operationInfoDAO.list(queryDTO);
    }

    @Override
    public Response saveOrUpdate(OperationInfo operationInfo) {
        if (operationInfo.getId() == null) {
            operationInfoDAO.insert(operationInfo);
        } else {
            operationInfoDAO.update(operationInfo);
        }
        return new Response(operationInfo.getId());
    }

    @Override
    protected void doDelete(List<Long> idList) {
        operationInfoDAO.batchDelete(idList);
    }

}
