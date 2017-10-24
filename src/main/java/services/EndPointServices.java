package services;

import entidades.EndPoint;

public class EndPointServices extends GestionDb<EndPoint> {

    private static EndPointServices instancia;

    private EndPointServices() {super(EndPoint.class);}

    public static EndPointServices getInstancia() {
        if(instancia==null){
            instancia = new EndPointServices();
        }
        return instancia;
    }
}
