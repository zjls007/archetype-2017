
import basic.JunitSpringContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * Created by zxj on 2017/2/7.
 */

public class JmsTest1 extends JunitSpringContext {

    @Resource
    private JmsTemplate jmsQueueTemplate;

    @Test
    public void test() {
        jmsQueueTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage("hello zxj!!!");
            }
        });
    }

}
