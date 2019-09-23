package App;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.jms.JMSException;

@SpringBootApplication
public class SpringBootApp {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApp.class, args);
        GreetingReceiver greetingReceiver = new GreetingReceiver();
        try {
            greetingReceiver.receiveMsg();
        }
        catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
