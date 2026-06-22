package cl.sanosysalvos.mascota_service.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.sanosysalvos.mascota_service.dto.MascotaRequestDTO;
import cl.sanosysalvos.mascota_service.dto.MascotaResponseDTO;
import cl.sanosysalvos.mascota_service.model.Mascota;
import cl.sanosysalvos.mascota_service.repository.MascotaRepository;

@Service
public class MascotaService {

    @Autowired
    private MascotaRepository mascotaRepository;

    // 1. POST: Registrar Mascota
    public MascotaResponseDTO registrarMascota(MascotaRequestDTO request) {
        Mascota mascota = new Mascota();
        mascota.setNombre(request.getNombre());
        mascota.setEspecie(request.getEspecie());
        mascota.setEdad(request.getEdad());
        mascota.setUsuarioId(request.getUsuarioId());

        Mascota mascotaGuardada = mascotaRepository.save(mascota);
        return mapearADTO(mascotaGuardada);
    }

    // 2. GET: Obtener todas las mascotas
    public List<MascotaResponseDTO> obtenerTodas() {
        return mascotaRepository.findAll().stream()
                .map(this::mapearADTO)
                .collect(Collectors.toList());
    }

    // 3. GET: Obtener por ID
    public MascotaResponseDTO obtenerPorId(Long id) {
        Mascota mascota = mascotaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada con ID: " + id));
        return mapearADTO(mascota);
    }

    // 4. PUT: Actualizar mascota
    public MascotaResponseDTO actualizarMascota(Long id, MascotaRequestDTO request) {
        Mascota mascota = mascotaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada con ID: " + id));

        mascota.setNombre(request.getNombre());
        mascota.setEspecie(request.getEspecie());
        mascota.setEdad(request.getEdad());
        mascota.setUsuarioId(request.getUsuarioId());

        Mascota mascotaActualizada = mascotaRepository.save(mascota);
        return mapearADTO(mascotaActualizada);
    }

    // 5. DELETE: Eliminar mascota
    public void eliminarMascota(Long id) {
        if (!mascotaRepository.existsById(id)) {
            throw new RuntimeException("Mascota no encontrada con ID: " + id);
        }
        mascotaRepository.deleteById(id);
    }

    // Método auxiliar privado para no repetir código
    private MascotaResponseDTO mapearADTO(Mascota mascota) {
        MascotaResponseDTO dto = new MascotaResponseDTO();
        dto.setId(mascota.getId());
        dto.setNombre(mascota.getNombre());
        dto.setEspecie(mascota.getEspecie());
        dto.setEdad(mascota.getEdad());
        dto.setUsuarioId(mascota.getUsuarioId());
        return dto;
    }
}