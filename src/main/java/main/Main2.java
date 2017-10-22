package main;

import org.apache.activemq.broker.BrokerService;
import javax.jms.JMSException;
import java.io.IOException;

public class Main2  {
    public static void main(String[] args)  throws IOException, JMSException {
        try {
            BrokerService broker = new BrokerService();
            broker.addConnector("tcp://localhost:61616");
            broker.start();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
