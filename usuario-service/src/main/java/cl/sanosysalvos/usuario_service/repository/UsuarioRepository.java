package cl.sanosysalvos.usuario_service.repository;

import cl.sanosysalvos.usuario_service.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Solo con hacer esto, Spring Boot ya nos regala los métodos para guardar, buscar, y eliminar.
}