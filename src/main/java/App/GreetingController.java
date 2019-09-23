package App;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.*;

@RestController
@RequestMapping(path="/GreetingMsg")
public class GreetingController {

    //private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
    private static String url = "tcp://broker-amq-tcp:61616";
    private static String queue = "greeting_queue";

    public void sendToQueue(String greetingMsg) throws JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("useraVk", "jFxwmHup", url);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination destination = session.createQueue(queue);

        MessageProducer producer = session.createProducer(destination);

        TextMessage message = session.createTextMessage(greetingMsg);
        producer.send(message);

        System.out.println("Sent message to queue");
        connection.close();
    }

    @PostMapping(path="/PostMsg", consumes="application/json", produces="application/json")
    public void postGreeting(@RequestBody Greetings greeting) throws JMSException {
        System.out.println("Received greeting: " + greeting.toString());
        sendToQueue(greeting.toString());
    }
}
