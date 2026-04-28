package cl.sanosysalvos.mascota_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.sanosysalvos.mascota_service.model.Reporte;

@Repository
public interface ReporteRepository extends JpaRepository<Reporte, Long> {
}