package cl.sanosysalvos.integracion_externa_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.sanosysalvos.integracion_externa_service.dto.OrganizacionRequestDTO;
import cl.sanosysalvos.integracion_externa_service.dto.OrganizacionResponseDTO;
import cl.sanosysalvos.integracion_externa_service.service.OrganizacionService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/organizaciones")
public class OrganizacionController {

    @Autowired
    private OrganizacionService service;

    @PostMapping
    public ResponseEntity<OrganizacionResponseDTO> registrarOrganizacion(@Valid @RequestBody OrganizacionRequestDTO requestDTO) {
        return new ResponseEntity<>(service.registrarOrganizacion(requestDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<OrganizacionResponseDTO>> listarOrganizaciones() {
        return new ResponseEntity<>(service.obtenerTodas(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrganizacionResponseDTO> obtenerOrganizacionPorId(@PathVariable Long id) {
        return new ResponseEntity<>(service.obtenerPorId(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrganizacionResponseDTO> actualizarOrganizacion(
            @PathVariable Long id, 
            @Valid @RequestBody OrganizacionRequestDTO requestDTO) {
        return new ResponseEntity<>(service.actualizarOrganizacion(id, requestDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarOrganizacion(@PathVariable Long id) {
        service.eliminarOrganizacion(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}