package cl.sanosysalvos.integracion_externa_service.controller;

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

import cl.sanosysalvos.integracion_externa_service.model.Organizacion;
import cl.sanosysalvos.integracion_externa_service.service.OrganizacionService;

@RestController
@RequestMapping("/api/organizaciones")
public class OrganizacionController {
    private final OrganizacionService service;

    public OrganizacionController(OrganizacionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Organizacion> registrar(@RequestBody Organizacion organizacion) {
        return new ResponseEntity<>(service.registrar(organizacion), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Organizacion>> listarTodas() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<Organizacion>> listarPorTipo(@PathVariable String tipo) {
        return ResponseEntity.ok(service.listarPorTipo(tipo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Organizacion> actualizar(@PathVariable Long id, @RequestBody Organizacion org) {
        return ResponseEntity.ok(service.actualizar(id, org));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}