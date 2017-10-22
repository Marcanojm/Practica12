package jms;

import main.Main;
import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.*;

public class Consumidor {

    ActiveMQConnectionFactory factory;
    Connection connection;
    Session session;
    Topic topic;
    MessageConsumer consumer;
    String cola;

    public Consumidor(String cola) {
        this.cola = cola;
    }

    public void conectar() throws JMSException {

        factory = new ActiveMQConnectionFactory("admin", "admin", "failover:tcp://localhost:61616");
        connection = factory.createConnection();
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        topic = session.createTopic(cola);
        consumer = session.createConsumer(topic);

        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                try {
                    TextMessage messageTexto = (TextMessage) message;
                    Main.enviarMensajeAClientesConectados(messageTexto.getText());
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        });
    }
}
