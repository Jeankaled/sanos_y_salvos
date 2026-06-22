package cl.sanosysalvos.datos_analitica_service.controller;

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

import cl.sanosysalvos.datos_analitica_service.dto.ReporteEstadisticoRequestDTO;
import cl.sanosysalvos.datos_analitica_service.dto.ReporteEstadisticoResponseDTO;
import cl.sanosysalvos.datos_analitica_service.service.ReporteEstadisticoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/estadisticas")
public class ReporteEstadisticoController {

    @Autowired
    private ReporteEstadisticoService service;

    @PostMapping
    public ResponseEntity<ReporteEstadisticoResponseDTO> crearReporte(@Valid @RequestBody ReporteEstadisticoRequestDTO requestDTO) {
        return new ResponseEntity<>(service.crearReporte(requestDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ReporteEstadisticoResponseDTO>> listarReportes() {
        return new ResponseEntity<>(service.obtenerTodos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReporteEstadisticoResponseDTO> obtenerReportePorId(@PathVariable Long id) {
        return new ResponseEntity<>(service.obtenerPorId(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReporteEstadisticoResponseDTO> actualizarReporte(
            @PathVariable Long id, 
            @Valid @RequestBody ReporteEstadisticoRequestDTO requestDTO) {
        return new ResponseEntity<>(service.actualizarReporte(id, requestDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReporte(@PathVariable Long id) {
        service.eliminarReporte(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}