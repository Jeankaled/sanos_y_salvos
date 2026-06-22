package cl.sanosysalvos.mascota_service.controller;

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

import cl.sanosysalvos.mascota_service.dto.MascotaRequestDTO;
import cl.sanosysalvos.mascota_service.dto.MascotaResponseDTO;
import cl.sanosysalvos.mascota_service.service.MascotaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/mascotas")
public class MascotaController {

    @Autowired
    private MascotaService mascotaService;

    @PostMapping
    public ResponseEntity<MascotaResponseDTO> registrarMascota(@Valid @RequestBody MascotaRequestDTO requestDTO) {
        return new ResponseEntity<>(mascotaService.registrarMascota(requestDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MascotaResponseDTO>> listarMascotas() {
        return new ResponseEntity<>(mascotaService.obtenerTodas(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MascotaResponseDTO> obtenerMascotaPorId(@PathVariable Long id) {
        return new ResponseEntity<>(mascotaService.obtenerPorId(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MascotaResponseDTO> actualizarMascota(
            @PathVariable Long id, 
            @Valid @RequestBody MascotaRequestDTO requestDTO) {
        return new ResponseEntity<>(mascotaService.actualizarMascota(id, requestDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMascota(@PathVariable Long id) {
        mascotaService.eliminarMascota(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}