import basic.JunitSpringContext;
import com.cy.dao.UserInfoDAO;
import com.cy.entity.UserInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zxj on 2017/2/7.
 */

public class UserInfoTest extends JunitSpringContext {

    @Autowired
    private UserInfoDAO userInfoDAO;

    @Test
    public void insert() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("a");
        userInfo.setPassword("a73fcf339640929207281fb8e038884806e2eb0840f2245694dbba1d5cc89e65");
        int result = userInfoDAO.insert(userInfo);
        System.out.println(result);
    }

    @Test
    public void selectById() {
        UserInfo userInfo = userInfoDAO.selectById(1L);
        System.out.println(userInfo);
    }

}
