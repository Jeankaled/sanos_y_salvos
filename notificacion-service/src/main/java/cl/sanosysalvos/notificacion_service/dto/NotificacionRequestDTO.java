package cl.sanosysalvos.notificacion_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class NotificacionRequestDTO {

    @NotBlank(message = "El mensaje de la notificación es obligatorio")
    private String mensaje;

    @NotNull(message = "El ID del usuario destino es obligatorio")
    private Long usuarioId;

    // Agregamos el campo 'leido' por si queremos actualizar su estado mediante un PUT
    private Boolean leido; 

    // Getters y Setters
    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

    public Boolean getLeido() { return leido; }
    public void setLeido(Boolean leido) { this.leido = leido; }
}