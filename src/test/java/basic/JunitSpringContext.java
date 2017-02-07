package basic;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by zxj on 2016/12/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-application-context.xml",
        "classpath:spring/spring-application-dao.xml"})
public class JunitSpringContext {
}
