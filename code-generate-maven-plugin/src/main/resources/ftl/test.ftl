import basic.JunitSpringContext;
import com.cy.dao.FinancePayReceiveDAO;
import com.cy.entity.FinancePayReceive;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by ${auth!} on ${(date?string("yyyy-MM-dd HH:mm:ss"))!}.
 */
public class ${beanName!}ServiceTest extends JunitSpringContext {

    @Resource
    private ${beanName!}DAO ${beanName?uncap_first!}DAO;

    private ${beanName!} getEntity() {
        ${beanName!} entity = new ${beanName!}();

        <#list setMethodInvokeStrList as item>
        ${item}
        </#list>
        return entity;
    }

    @Test
    public void insert() {
        ${beanName!} entity = getEntity();
        int result = financePayReceiveDAO.insert(entity);
        Assert.assertEquals(result, 1);
    }

    @Test
    public void batchInsert() {
        List<${beanName!}> list = new ArrayList<${beanName!}>();
        list.add(getEntity());
        int result = financePayReceiveDAO.batchInsert(list);
        Assert.assertEquals(result, list.size());
    }

    @Test
    public void delete() {

    }

    @Test
    public void batchDelete() {

    }

    @Test
    public void update() {

    }

    @Test
    public void getById() {

    }

    @Test
    public void getByIdList() {

    }

    @Test
    public void getByOrderNum() {

    }

}
