package cl.sanosysalvos.datos_analitica_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.sanosysalvos.datos_analitica_service.model.ReporteEstadistico;

public interface ReporteEstadisticoRepository extends JpaRepository<ReporteEstadistico, Long> {
    List<ReporteEstadistico> findByComuna(String comuna);
}