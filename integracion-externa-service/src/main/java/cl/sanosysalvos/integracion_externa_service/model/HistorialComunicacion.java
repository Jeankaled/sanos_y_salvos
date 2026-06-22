package cl.sanosysalvos.integracion_externa_service.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "historial_comunicaciones")
public class HistorialComunicacion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String servicioDestino; // Ej: "API_VETERINARIAS_CHILE", "TWILIO_SMS"
    private String tipoOperacion; // Ej: "CONSULTA_CHIP", "ENVIO_ALERTA"
    private String estadoRespuesta; // Ej: "EXITO", "ERROR_500"
    private LocalDateTime fechaLlamada;

    public HistorialComunicacion() {
        this.fechaLlamada = LocalDateTime.now();
    }
    
    // --- Getters y Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServicioDestino() {
        return servicioDestino;
    }

    public void setServicioDestino(String servicioDestino) {
        this.servicioDestino = servicioDestino;
    }

    public String getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(String tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public String getEstadoRespuesta() {
        return estadoRespuesta;
    }

    public void setEstadoRespuesta(String estadoRespuesta) {
        this.estadoRespuesta = estadoRespuesta;
    }

    public LocalDateTime getFechaLlamada() {
        return fechaLlamada;
    }

    public void setFechaLlamada(LocalDateTime fechaLlamada) {
        this.fechaLlamada = fechaLlamada;
    }
}