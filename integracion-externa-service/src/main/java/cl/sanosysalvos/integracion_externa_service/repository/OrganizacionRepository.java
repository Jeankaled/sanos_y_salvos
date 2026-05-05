package cl.sanosysalvos.integracion_externa_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.sanosysalvos.integracion_externa_service.model.Organizacion;

public interface OrganizacionRepository extends JpaRepository<Organizacion, Long> {
    List<Organizacion> findByTipo(String tipo);
}