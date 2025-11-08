package com.joseluisgs.walaspringboot.repositorios;

import com.joseluisgs.walaspringboot.modelos.Compra;
import com.joseluisgs.walaspringboot.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Respositorio de operaciones DAO/CRUD de Usuario
public interface CompraRepository extends JpaRepository<Compra, Long> {

    // Añadimos las siguientes oprraciones a parte de las propias CRUD y Paginación por defecto implementadas

    // Listar todas las compras de un usuario: where usuario...
    List<Compra> findByPropietario(Usuario propietario);
}
