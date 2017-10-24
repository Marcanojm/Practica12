package main;

import org.apache.activemq.broker.BrokerService;
import org.h2.tools.Server;

import javax.jms.JMSException;
import java.io.IOException;
import java.sql.SQLException;

public class Main2  {
    public static void main(String[] args)  throws IOException, JMSException {
        try {
            abrirPuerto();
            BrokerService broker = new BrokerService();
            broker.addConnector("tcp://localhost:61616");
            broker.start();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public static void abrirPuerto() {

        try {
            Server.createTcpServer("-tcpPort", "9092", "-tcpAllowOthers").start();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
