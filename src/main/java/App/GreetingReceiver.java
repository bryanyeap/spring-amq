package App;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class GreetingReceiver {

    private static String url = "tcp://broker-amq-tcp:61616";
    private static String subject = "greeting_queue";

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(url);
        connectionFactory.setUserName("useraVk");
        connectionFactory.setPassword("jFxwmHup");
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("greeting_queue");
        MessageConsumer consumer = session.createConsumer(destination);
        consumer.setMessageListener(new messageReceiver());

        connection.close();
    }
}
