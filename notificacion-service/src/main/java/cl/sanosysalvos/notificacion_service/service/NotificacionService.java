package cl.sanosysalvos.notificacion_service.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.sanosysalvos.notificacion_service.dto.NotificacionRequestDTO;
import cl.sanosysalvos.notificacion_service.dto.NotificacionResponseDTO;
import cl.sanosysalvos.notificacion_service.model.Notificacion;
import cl.sanosysalvos.notificacion_service.repository.NotificacionRepository;

@Service
public class NotificacionService {

    @Autowired
    private NotificacionRepository notificacionRepository;

    // 1. POST: Crear Notificación
    public NotificacionResponseDTO crearNotificacion(NotificacionRequestDTO request) {
        Notificacion notificacion = new Notificacion();
        notificacion.setMensaje(request.getMensaje());
        notificacion.setUsuarioId(request.getUsuarioId());
        // La fecha y el estado 'leido=false' ya se setean solos por tu constructor en el Model

        Notificacion guardada = notificacionRepository.save(notificacion);
        return mapearADTO(guardada);
    }

    // 2. GET: Listar todas
    public List<NotificacionResponseDTO> obtenerTodas() {
        return notificacionRepository.findAll().stream()
                .map(this::mapearADTO)
                .collect(Collectors.toList());
    }

    // 3. GET: Obtener por ID
    public NotificacionResponseDTO obtenerPorId(Long id) {
        Notificacion notificacion = notificacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notificación no encontrada con ID: " + id));
        return mapearADTO(notificacion);
    }

    // 4. PUT: Actualizar Notificación (Usualmente para marcar como leída)
    public NotificacionResponseDTO actualizarNotificacion(Long id, NotificacionRequestDTO request) {
        Notificacion notificacion = notificacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notificación no encontrada con ID: " + id));

        notificacion.setMensaje(request.getMensaje());
        notificacion.setUsuarioId(request.getUsuarioId());
        
        // Si el request nos manda un estado de lectura, lo actualizamos
        if (request.getLeido() != null) {
            notificacion.setLeido(request.getLeido());
        }

        Notificacion actualizada = notificacionRepository.save(notificacion);
        return mapearADTO(actualizada);
    }

    // 5. DELETE: Eliminar Notificación
    public void eliminarNotificacion(Long id) {
        if (!notificacionRepository.existsById(id)) {
            throw new RuntimeException("Notificación no encontrada con ID: " + id);
        }
        notificacionRepository.deleteById(id);
    }

    // Método auxiliar
    private NotificacionResponseDTO mapearADTO(Notificacion notificacion) {
        NotificacionResponseDTO dto = new NotificacionResponseDTO();
        dto.setId(notificacion.getId());
        dto.setMensaje(notificacion.getMensaje());
        dto.setFechaEnvio(notificacion.getFechaEnvio());
        dto.setLeido(notificacion.getLeido());
        dto.setUsuarioId(notificacion.getUsuarioId());
        return dto;
    }
}