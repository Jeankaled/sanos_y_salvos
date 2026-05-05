package cl.sanosysalvos.datos_analitica_service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.sanosysalvos.datos_analitica_service.model.ReporteEstadistico;
import cl.sanosysalvos.datos_analitica_service.service.ReporteEstadisticoService;

@RestController
@RequestMapping("/api/analiticas")
public class ReporteEstadisticoController {
    
    private final ReporteEstadisticoService service;

    public ReporteEstadisticoController(ReporteEstadisticoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ReporteEstadistico> registrar(@RequestBody ReporteEstadistico reporte) {
        return new ResponseEntity<>(service.registrar(reporte), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ReporteEstadistico>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/comuna/{comuna}")
    public ResponseEntity<List<ReporteEstadistico>> listarPorComuna(@PathVariable String comuna) {
        return ResponseEntity.ok(service.listarPorComuna(comuna));
    }
}