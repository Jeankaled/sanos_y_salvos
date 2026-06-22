package cl.sanosysalvos.datos_analitica_service.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.sanosysalvos.datos_analitica_service.dto.ReporteEstadisticoRequestDTO;
import cl.sanosysalvos.datos_analitica_service.dto.ReporteEstadisticoResponseDTO;
import cl.sanosysalvos.datos_analitica_service.model.ReporteEstadistico;
import cl.sanosysalvos.datos_analitica_service.repository.ReporteEstadisticoRepository;

@Service
public class ReporteEstadisticoService {

    @Autowired
    private ReporteEstadisticoRepository repository;

    // 1. POST: Crear Registro Estadístico
    public ReporteEstadisticoResponseDTO crearReporte(ReporteEstadisticoRequestDTO request) {
        ReporteEstadistico reporte = new ReporteEstadistico();
        reporte.setComuna(request.getComuna());
        reporte.setEspecie(request.getEspecie());
        reporte.setCantidadExtravios(request.getCantidadExtravios());
        reporte.setCantidadRecuperados(request.getCantidadRecuperados());

        ReporteEstadistico guardado = repository.save(reporte);
        return mapearADTO(guardado);
    }

    // 2. GET: Listar todos
    public List<ReporteEstadisticoResponseDTO> obtenerTodos() {
        return repository.findAll().stream()
                .map(this::mapearADTO)
                .collect(Collectors.toList());
    }

    // 3. GET: Obtener por ID
    public ReporteEstadisticoResponseDTO obtenerPorId(Long id) {
        ReporteEstadistico reporte = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reporte estadístico no encontrado con ID: " + id));
        return mapearADTO(reporte);
    }

    // 4. PUT: Actualizar Estadísticas
    public ReporteEstadisticoResponseDTO actualizarReporte(Long id, ReporteEstadisticoRequestDTO request) {
        ReporteEstadistico reporte = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reporte estadístico no encontrado con ID: " + id));

        reporte.setComuna(request.getComuna());
        reporte.setEspecie(request.getEspecie());
        reporte.setCantidadExtravios(request.getCantidadExtravios());
        reporte.setCantidadRecuperados(request.getCantidadRecuperados());

        ReporteEstadistico actualizado = repository.save(reporte);
        return mapearADTO(actualizado);
    }

    // 5. DELETE: Eliminar Estadísticas
    public void eliminarReporte(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Reporte estadístico no encontrado con ID: " + id);
        }
        repository.deleteById(id);
    }

    // Método auxiliar
    private ReporteEstadisticoResponseDTO mapearADTO(ReporteEstadistico reporte) {
        ReporteEstadisticoResponseDTO dto = new ReporteEstadisticoResponseDTO();
        dto.setId(reporte.getId());
        dto.setComuna(reporte.getComuna());
        dto.setEspecie(reporte.getEspecie());
        dto.setCantidadExtravios(reporte.getCantidadExtravios());
        dto.setCantidadRecuperados(reporte.getCantidadRecuperados());
        dto.setMesRegistro(reporte.getMesRegistro());
        return dto;
    }
}