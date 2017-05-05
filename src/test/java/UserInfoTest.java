import basic.JunitSpringContext;
import com.cy.service.UserInfoService;
import com.cy.web.dto.UserLoginDTO;
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
        UserLoginDTO dto = new UserLoginDTO();
        dto.setCredentials("zxj1");
        dto.setPrincipal("2");
        Long id = userService.regeist(dto);
        System.out.println(id);
    }

}
