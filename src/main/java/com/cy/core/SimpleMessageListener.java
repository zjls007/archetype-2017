package com.cy.core;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by zxj on 2017/3/14.
 */
public class SimpleMessageListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        try {
            TextMessage msg = (TextMessage) message;
            System.out.println(msg.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
