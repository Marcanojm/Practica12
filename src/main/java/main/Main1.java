package main;

import jms.Productor;
import javax.jms.JMSException;
import java.io.IOException;

public class Main1 {
    public static void main(String[] args)  throws IOException, JMSException {
        String cola = "notificacion_sensores.cola";

        new Productor().enviarMensaje(cola, Integer.parseInt(args[0]));
    }
}
