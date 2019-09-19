package App;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.*;

@RestController
@RequestMapping(path="/GreetingMsg")
public class GreetingController {

    private static String url = "http://broker-amq-tcp-spring-amq-redis-hello.apps.mta-eam-eval.rhmi.io/";
    private static String queue = "greeting_queue";

    public void sendToQueue(String greetingMsg) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
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
