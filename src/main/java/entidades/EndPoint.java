package entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
public class EndPoint implements Serializable {

    @Id
    @GeneratedValue
    private long id;
    private String fechaGeneracion;
    private int IdDispositivo;
    private Double temperatura;
    private Double humedad;

    public EndPoint() {
    }

    public EndPoint(String fechaGeneracion, int idDispositivo, Double temperatura, Double humedad) {
        this.fechaGeneracion = fechaGeneracion;
        IdDispositivo = idDispositivo;
        this.temperatura = temperatura;
        this.humedad = humedad;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(String fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public int getIdDispositivo() {
        return IdDispositivo;
    }

    public void setIdDispositivo(int idDispositivo) {
        IdDispositivo = idDispositivo;
    }

    public Double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Double temperatura) {
        this.temperatura = temperatura;
    }

    public Double getHumedad() {
        return humedad;
    }

    public void setHumedad(Double humedad) {
        this.humedad = humedad;
    }

    @Override
    public String toString() {
        return "EndPoint{" +
                "id=" + id +
                ", fechaGeneracion='" + fechaGeneracion + '\'' +
                ", IdDispositivo='" + IdDispositivo + '\'' +
                ", temperatura='" + temperatura + '\'' +
                ", humedad='" + humedad + '\'' +
                '}';
    }
}
