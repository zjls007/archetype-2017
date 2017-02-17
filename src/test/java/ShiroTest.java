import basic.JunitSpringContext;
import org.apache.shiro.mgt.SecurityManager;
import org.junit.Test;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

/**
 * Created by zxj on 2017/2/17.
 */
@WebAppConfiguration
public class ShiroTest extends JunitSpringContext {

    @Resource
    SecurityManager securityManager;

    @Test
    public void test() {
        System.out.println(securityManager);
    }

}
