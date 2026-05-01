package cl.sanosysalvos.notificacion_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.sanosysalvos.notificacion_service.model.Notificacion;

public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {
    // Método útil para buscar notificaciones de un usuario específico
    List<Notificacion> findByUsuarioId(Long usuarioId);
}