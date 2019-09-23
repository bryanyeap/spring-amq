package App;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class messageReceiver implements MessageListener {

    @Override
    public void onMessage(Message message) {
        try {
            TextMessage textMsg = (TextMessage)message;
            System.out.println(textMsg.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
