package com.joseluisgs.walaspringboot.repositories;

import com.joseluisgs.walaspringboot.models.Compra;
import com.joseluisgs.walaspringboot.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompraRepository extends JpaRepository<Compra, Long> {
    List<Compra> findByPropietario(Usuario propietario);
}
