package basic;

import com.cy.dao.UserInfoDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

/**
 * Created by zxj on 2016/12/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(
        {"file:src/main/webapp/WEB-INF/config/spring-application-*.xml"})
//@ContextConfiguration({"classpath:spring/spring-application-*.xml"})
public class JunitSpringContext {

}

