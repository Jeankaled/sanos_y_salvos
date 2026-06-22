package cl.sanosysalvos.usuario_service.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.sanosysalvos.usuario_service.dto.UsuarioRequestDTO;
import cl.sanosysalvos.usuario_service.dto.UsuarioResponseDTO;
import cl.sanosysalvos.usuario_service.model.Usuario;
import cl.sanosysalvos.usuario_service.repository.UsuarioRepository; // ¡Aquí está el import que faltaba!

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // 1. MÉTODO POST: Crear Usuario
    public UsuarioResponseDTO registrarUsuario(UsuarioRequestDTO request) {
        Usuario usuario = new Usuario();
        usuario.setNombre(request.getNombre());
        usuario.setEmail(request.getEmail());
        usuario.setTelefono(request.getTelefono());

        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        UsuarioResponseDTO response = new UsuarioResponseDTO();
        response.setId(usuarioGuardado.getId());
        response.setNombre(usuarioGuardado.getNombre());
        response.setEmail(usuarioGuardado.getEmail());
        response.setTelefono(usuarioGuardado.getTelefono());

        return response;
    }

    // 2. MÉTODO GET: Obtener todos
    public List<UsuarioResponseDTO> obtenerTodos() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        
        return usuarios.stream().map(usuario -> {
            UsuarioResponseDTO dto = new UsuarioResponseDTO();
            dto.setId(usuario.getId());
            dto.setNombre(usuario.getNombre());
            dto.setEmail(usuario.getEmail());
            dto.setTelefono(usuario.getTelefono());
            return dto;
        }).collect(Collectors.toList());
    }

    // 3. MÉTODO GET: Obtener por ID
    public UsuarioResponseDTO obtenerPorId(Long id) {
        // Buscamos el usuario, si no existe lanzamos un error
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
        
        UsuarioResponseDTO dto = new UsuarioResponseDTO();
        dto.setId(usuario.getId());
        dto.setNombre(usuario.getNombre());
        dto.setEmail(usuario.getEmail());
        dto.setTelefono(usuario.getTelefono());
        return dto;
    }

    // 4. MÉTODO PUT: Actualizar Usuario
    public UsuarioResponseDTO actualizarUsuario(Long id, UsuarioRequestDTO request) {
        // Primero verificamos que exista
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
        
        // Actualizamos los datos de la entidad
        usuario.setNombre(request.getNombre());
        usuario.setEmail(request.getEmail());
        usuario.setTelefono(request.getTelefono());
        
        // Guardamos los cambios
        Usuario usuarioActualizado = usuarioRepository.save(usuario);
        
        // Convertimos a DTO para responder
        UsuarioResponseDTO response = new UsuarioResponseDTO();
        response.setId(usuarioActualizado.getId());
        response.setNombre(usuarioActualizado.getNombre());
        response.setEmail(usuarioActualizado.getEmail());
        response.setTelefono(usuarioActualizado.getTelefono());
        return response;
    }

    // 5. MÉTODO DELETE: Eliminar Usuario
    public void eliminarUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado con ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }
}