package cl.sanosysalvos.mascota_service.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.sanosysalvos.mascota_service.dto.ReporteRequestDTO;
import cl.sanosysalvos.mascota_service.dto.ReporteResponseDTO;
import cl.sanosysalvos.mascota_service.model.Mascota;
import cl.sanosysalvos.mascota_service.model.Reporte;
import cl.sanosysalvos.mascota_service.repository.MascotaRepository;
import cl.sanosysalvos.mascota_service.repository.ReporteRepository;

@Service
public class ReporteService {

    @Autowired
    private ReporteRepository reporteRepository;

    @Autowired
    private MascotaRepository mascotaRepository;

    // 1. POST: Crear Reporte
    public ReporteResponseDTO crearReporte(ReporteRequestDTO request) {
        // Validar que la mascota exista
        Mascota mascota = mascotaRepository.findById(request.getMascotaId())
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada con ID: " + request.getMascotaId()));

        Reporte reporte = new Reporte();
        reporte.setEstado(request.getEstado());
        reporte.setUbicacion(request.getUbicacion());
        reporte.setFecha(LocalDateTime.now());
        reporte.setMascota(mascota);

        Reporte reporteGuardado = reporteRepository.save(reporte);
        return mapearADTO(reporteGuardado);
    }

    // 2. GET: Listar todos
    public List<ReporteResponseDTO> obtenerTodos() {
        return reporteRepository.findAll().stream()
                .map(this::mapearADTO)
                .collect(Collectors.toList());
    }

    // 3. GET: Obtener por ID
    public ReporteResponseDTO obtenerPorId(Long id) {
        Reporte reporte = reporteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reporte no encontrado con ID: " + id));
        return mapearADTO(reporte);
    }

    // 4. PUT: Actualizar Reporte
    public ReporteResponseDTO actualizarReporte(Long id, ReporteRequestDTO request) {
        Reporte reporte = reporteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reporte no encontrado con ID: " + id));

        Mascota mascota = mascotaRepository.findById(request.getMascotaId())
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada con ID: " + request.getMascotaId()));

        reporte.setEstado(request.getEstado());
        reporte.setUbicacion(request.getUbicacion());
        reporte.setMascota(mascota);

        Reporte reporteActualizado = reporteRepository.save(reporte);
        return mapearADTO(reporteActualizado);
    }

    // 5. DELETE: Eliminar Reporte
    public void eliminarReporte(Long id) {
        if (!reporteRepository.existsById(id)) {
            throw new RuntimeException("Reporte no encontrado con ID: " + id);
        }
        reporteRepository.deleteById(id);
    }

    // Método auxiliar para mapear Entidad a DTO
    private ReporteResponseDTO mapearADTO(Reporte reporte) {
        ReporteResponseDTO dto = new ReporteResponseDTO();
        dto.setId(reporte.getId());
        dto.setEstado(reporte.getEstado());
        dto.setUbicacion(reporte.getUbicacion());
        dto.setFecha(reporte.getFecha());
        dto.setMascotaId(reporte.getMascota().getId()); // Solo exponemos el ID de la mascota
        return dto;
    }
}