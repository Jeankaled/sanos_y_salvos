package cl.sanosysalvos.mascota_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.sanosysalvos.mascota_service.model.Mascota;
import cl.sanosysalvos.mascota_service.model.Reporte;
import cl.sanosysalvos.mascota_service.repository.MascotaRepository;
import cl.sanosysalvos.mascota_service.repository.ReporteRepository;

@Service
public class MascotaService {

    @Autowired
    private MascotaRepository mascotaRepository;

    @Autowired
    private ReporteRepository reporteRepository;

    public Mascota registrarMascota(Mascota mascota) {
        return mascotaRepository.save(mascota); // Guarda la mascota
    }

    public List<Mascota> obtenerTodas() {
        return mascotaRepository.findAll();
    }

    public Reporte registrarReporte(Long mascotaId, Reporte reporte) {
        Mascota mascota = mascotaRepository.findById(mascotaId).orElseThrow();
        reporte.setMascota(mascota);
        return reporteRepository.save(reporte);
    }
    // Método para Actualizar (Update)
    public Mascota actualizarMascota(Long id, Mascota mascotaActualizada) {
        // Buscamos si la mascota existe, si existe cambiamos sus datos y guardamos
        return mascotaRepository.findById(id).map(mascotaExistente -> {
            mascotaExistente.setNombre(mascotaActualizada.getNombre());
            mascotaExistente.setEspecie(mascotaActualizada.getEspecie());
            mascotaExistente.setEdad(mascotaActualizada.getEdad());
            // Nota: Por regla de negocio, usualmente no se permite cambiar el dueño (usuarioId)
            return mascotaRepository.save(mascotaExistente);
        }).orElseThrow(() -> new RuntimeException("Mascota no encontrada"));
    }

    // Método para Eliminar (Delete)
    public void eliminarMascota(Long id) {
        mascotaRepository.deleteById(id);
    }


}