package cl.sanosysalvos.datos_analitica_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.sanosysalvos.datos_analitica_service.model.ReporteEstadistico;
import cl.sanosysalvos.datos_analitica_service.repository.ReporteEstadisticoRepository;

@Service
public class ReporteEstadisticoService {
    private final ReporteEstadisticoRepository repository;

    public ReporteEstadisticoService(ReporteEstadisticoRepository repository) {
        this.repository = repository;
    }

    public ReporteEstadistico registrar(ReporteEstadistico reporte) {
        return repository.save(reporte);
    }

    public List<ReporteEstadistico> listarTodos() {
        return repository.findAll();
    }

    public List<ReporteEstadistico> listarPorComuna(String comuna) {
        return repository.findByComuna(comuna);
    }
}