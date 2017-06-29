package basic;

import com.cy.dao.FinancePayReceiveDAO;
import com.cy.dao.UserInfoDAO;
import com.cy.entity.FinancePayReceive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by zxj on 2016/12/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(
        {"file:src/main/webapp/WEB-INF/config/spring-application-*.xml"})
//@ContextConfiguration({"classpath:spring/spring-application-*.xml"})
public class JunitSpringContext {

    @Resource
    private FinancePayReceiveDAO financePayReceiveDAO;

    @Test
    public void test() {

        FinancePayReceive r = financePayReceiveDAO.selectById(621l);
        System.out.println(r);
    }

}

