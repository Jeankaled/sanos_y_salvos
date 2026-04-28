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
}