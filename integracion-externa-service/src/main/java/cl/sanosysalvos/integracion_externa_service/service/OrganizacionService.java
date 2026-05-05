package cl.sanosysalvos.integracion_externa_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.sanosysalvos.integracion_externa_service.model.Organizacion;
import cl.sanosysalvos.integracion_externa_service.repository.OrganizacionRepository;

@Service
public class OrganizacionService {
    private final OrganizacionRepository repository;

    public OrganizacionService(OrganizacionRepository repository) {
        this.repository = repository;
    }

    public Organizacion registrar(Organizacion organizacion) {
        return repository.save(organizacion);
    }

    public List<Organizacion> listarTodas() {
        return repository.findAll();
    }

    public List<Organizacion> listarPorTipo(String tipo) {
        return repository.findByTipo(tipo);
    }

    public Organizacion actualizar(Long id, Organizacion datosActualizados) {
        return repository.findById(id).map(org -> {
            org.setNombre(datosActualizados.getNombre());
            org.setTipo(datosActualizados.getTipo());
            org.setDireccion(datosActualizados.getDireccion());
            org.setTelefono(datosActualizados.getTelefono());
            org.setEmail(datosActualizados.getEmail());
            return repository.save(org);
        }).orElseThrow(() -> new RuntimeException("Organización no encontrada"));
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}