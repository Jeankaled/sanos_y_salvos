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

import cl.sanosysalvos.mascota_service.dto.ReporteRequestDTO;
import cl.sanosysalvos.mascota_service.dto.ReporteResponseDTO;
import cl.sanosysalvos.mascota_service.service.ReporteService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @PostMapping
    public ResponseEntity<ReporteResponseDTO> crearReporte(@Valid @RequestBody ReporteRequestDTO requestDTO) {
        return new ResponseEntity<>(reporteService.crearReporte(requestDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ReporteResponseDTO>> listarReportes() {
        return new ResponseEntity<>(reporteService.obtenerTodos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReporteResponseDTO> obtenerReportePorId(@PathVariable Long id) {
        return new ResponseEntity<>(reporteService.obtenerPorId(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReporteResponseDTO> actualizarReporte(
            @PathVariable Long id, 
            @Valid @RequestBody ReporteRequestDTO requestDTO) {
        return new ResponseEntity<>(reporteService.actualizarReporte(id, requestDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReporte(@PathVariable Long id) {
        reporteService.eliminarReporte(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}