package com.joseluisgs.walaspringboot.repositories;

import com.joseluisgs.walaspringboot.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findFirstByEmail(String email);
}
