package cl.sanosysalvos.integracion_externa_service.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.sanosysalvos.integracion_externa_service.dto.OrganizacionRequestDTO;
import cl.sanosysalvos.integracion_externa_service.dto.OrganizacionResponseDTO;
import cl.sanosysalvos.integracion_externa_service.model.Organizacion;
import cl.sanosysalvos.integracion_externa_service.repository.OrganizacionRepository;

@Service
public class OrganizacionService {

    @Autowired
    private OrganizacionRepository repository;

    public OrganizacionResponseDTO registrarOrganizacion(OrganizacionRequestDTO request) {
        Organizacion organizacion = new Organizacion();
        organizacion.setNombre(request.getNombre());
        organizacion.setTipo(request.getTipo());
        organizacion.setDireccion(request.getDireccion());
        organizacion.setTelefono(request.getTelefono());
        organizacion.setEmail(request.getEmail());

        Organizacion guardada = repository.save(organizacion);
        return mapearADTO(guardada);
    }

    public List<OrganizacionResponseDTO> obtenerTodas() {
        return repository.findAll().stream().map(this::mapearADTO).collect(Collectors.toList());
    }

    public OrganizacionResponseDTO obtenerPorId(Long id) {
        Organizacion organizacion = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Organización no encontrada con ID: " + id));
        return mapearADTO(organizacion);
    }

    public OrganizacionResponseDTO actualizarOrganizacion(Long id, OrganizacionRequestDTO request) {
        Organizacion organizacion = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Organización no encontrada con ID: " + id));

        organizacion.setNombre(request.getNombre());
        organizacion.setTipo(request.getTipo());
        organizacion.setDireccion(request.getDireccion());
        organizacion.setTelefono(request.getTelefono());
        organizacion.setEmail(request.getEmail());

        Organizacion actualizada = repository.save(organizacion);
        return mapearADTO(actualizada);
    }

    public void eliminarOrganizacion(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Organización no encontrada con ID: " + id);
        }
        repository.deleteById(id);
    }

    private OrganizacionResponseDTO mapearADTO(Organizacion org) {
        OrganizacionResponseDTO dto = new OrganizacionResponseDTO();
        dto.setId(org.getId());
        dto.setNombre(org.getNombre());
        dto.setTipo(org.getTipo());
        dto.setDireccion(org.getDireccion());
        dto.setTelefono(org.getTelefono());
        dto.setEmail(org.getEmail());
        dto.setFechaRegistro(org.getFechaRegistro());
        return dto;
    }
}