package cl.sanosysalvos.notificacion_service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.sanosysalvos.notificacion_service.model.Notificacion;
import cl.sanosysalvos.notificacion_service.service.NotificacionService;

@RestController
@RequestMapping("/api/notificaciones")
public class NotificacionController {
    private final NotificacionService service;

    public NotificacionController(NotificacionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Notificacion> crear(@RequestBody Notificacion notificacion) {
        return new ResponseEntity<>(service.crearNotificacion(notificacion), HttpStatus.CREATED);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Notificacion>> listar(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(service.listarPorUsuario(usuarioId));
    }

    @PutMapping("/{id}/leido")
    public ResponseEntity<Notificacion> leer(@PathVariable Long id) {
        return ResponseEntity.ok(service.marcarComoLeida(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminarNotificacion(id);
        return ResponseEntity.noContent().build();
    }
}