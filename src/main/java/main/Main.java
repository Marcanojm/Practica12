package main;

import jms.Consumidor;
import websocket.ServidorMensajesWebSocketHandler;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
import org.eclipse.jetty.websocket.api.Session;
import javax.jms.JMSException;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static spark.Spark.get;
import static spark.Spark.staticFiles;
import static spark.Spark.webSocket;

public class Main {

    public static List<Session> usuariosConectados = new ArrayList<>();

    public static void main(String[] args) throws IOException, JMSException  {
        String cola = "notificacion_sensores.cola";

        staticFiles.location("/publico");
        final Configuration configuration = new Configuration(new Version(2, 3, 0));
        configuration.setClassForTemplateLoading(Main.class, "/templates");

        webSocket("/mensajeServidor", ServidorMensajesWebSocketHandler.class);

        get("/", (request, response) -> {
            Template resultTemplate = configuration.getTemplate("Graficos.html");
            StringWriter writer = new StringWriter();
            Map<String, Object> attributes = new HashMap<>();

            resultTemplate.process(attributes, writer);
            return writer;
        });

        Consumidor consumidor=new Consumidor(cola);
        consumidor.conectar();
    }

    public static void enviarMensajeAClientesConectados(String mensaje){
        for(Session sesionConectada : usuariosConectados){
            try {
                sesionConectada.getRemote().sendString(mensaje);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
