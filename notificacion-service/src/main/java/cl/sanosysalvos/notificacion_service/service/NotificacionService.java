package cl.sanosysalvos.notificacion_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.sanosysalvos.notificacion_service.model.Notificacion;
import cl.sanosysalvos.notificacion_service.repository.NotificacionRepository;

@Service
public class NotificacionService {
    private final NotificacionRepository repository;

    public NotificacionService(NotificacionRepository repository) {
        this.repository = repository;
    }

    public Notificacion crearNotificacion(Notificacion notificacion) {
        return repository.save(notificacion);
    }

    public List<Notificacion> listarPorUsuario(Long usuarioId) {
        return repository.findByUsuarioId(usuarioId);
    }

    public Notificacion marcarComoLeida(Long id) {
        return repository.findById(id).map(n -> {
            n.setLeido(true);
            return repository.save(n);
        }).orElseThrow(() -> new RuntimeException("Notificación no encontrada"));
    }

    public void eliminarNotificacion(Long id) {
        repository.deleteById(id);
    }
}