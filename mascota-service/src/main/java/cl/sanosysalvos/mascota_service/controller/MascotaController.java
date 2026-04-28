package cl.sanosysalvos.mascota_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import cl.sanosysalvos.mascota_service.model.Mascota;
import cl.sanosysalvos.mascota_service.model.Reporte;
import cl.sanosysalvos.mascota_service.service.MascotaService;

@RestController
@RequestMapping("/api/mascotas")
public class MascotaController {

    @Autowired
    private MascotaService mascotaService;

    @PostMapping
    public ResponseEntity<Mascota> registrarMascota(@RequestBody Mascota mascota) {
        return new ResponseEntity<>(mascotaService.registrarMascota(mascota), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Mascota>> listarMascotas() {
        return new ResponseEntity<>(mascotaService.obtenerTodas(), HttpStatus.OK);
    }

    @PostMapping("/{id}/reportes")
    public ResponseEntity<Reporte> agregarReporte(@PathVariable Long id, @RequestBody Reporte reporte) {
        return new ResponseEntity<>(mascotaService.registrarReporte(id, reporte), HttpStatus.CREATED);
    }

    // Endpoint para Actualizar (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Mascota> actualizarMascota(@PathVariable Long id, @RequestBody Mascota mascota) {
        Mascota mascotaActualizada = mascotaService.actualizarMascota(id, mascota);
        return new ResponseEntity<>(mascotaActualizada, HttpStatus.OK);
    }

    // Endpoint para Eliminar (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMascota(@PathVariable Long id) {
        mascotaService.eliminarMascota(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Retorna un 204 Sin Contenido
    }


}