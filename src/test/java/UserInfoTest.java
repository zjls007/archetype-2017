import basic.JunitSpringContext;
import com.cy.service.UserInfoService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zxj on 2017/2/7.
 */

public class UserInfoTest extends JunitSpringContext {

    @Autowired
    private UserInfoService userService;

    @Test
    public void regeist() {
    }

}
