package jms;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.json.JSONException;
import org.json.JSONObject;
import javax.jms.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Productor {

    public Productor(){

    }

    public void enviarMensaje(String cola, int sensor) throws JMSException {

        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        Connection connection = factory.createConnection("admin", "admin");
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(cola);
        MessageProducer producer = session.createProducer(topic);

        while(true) {
            try {
                TimeUnit.SECONDS.sleep(1);
                JSONObject json = new JSONObject();

                try {
                    DateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    json.put("fechaGeneracion", formato.format(new Date()));
                    json.put("IdDispositivo", sensor);
                    json.put("temperatura", Math.ceil(Math.random() * 41));
                    json.put("humedad",  Math.ceil(Math.random() * 15));

                    System.out.println(json);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                TextMessage message = session.createTextMessage(json.toString());
                producer.send(message);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
