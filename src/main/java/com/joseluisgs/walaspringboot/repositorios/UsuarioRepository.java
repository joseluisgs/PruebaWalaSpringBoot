package com.joseluisgs.walaspringboot.repositorios;

import com.joseluisgs.walaspringboot.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

// Respositorio de operaciones DAO/CRUD de Usuario
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Añadimos las siguientes oprraciones a parte de las propias CRUD y Paginación por defecto implementadas

    //Buscar usuario por email: where email...
    Usuario findFirstByEmail(String email);
}
