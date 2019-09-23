package App;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import javax.jms.JMSException;

public class StartUps {
    @EventListener(ApplicationReadyEvent.class)
    public void onStartUp() {
        GreetingReceiver greetingReceiver = new GreetingReceiver();
        try{
            greetingReceiver.receiveMsg();
        }
        catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
