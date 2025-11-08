package com.joseluisgs.walaspringboot.repositorios;

import com.joseluisgs.walaspringboot.modelos.Compra;
import com.joseluisgs.walaspringboot.modelos.Producto;
import com.joseluisgs.walaspringboot.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Respositorio de operaciones DAO/CRUD de Usuario
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    // Añadimos las siguientes oprraciones a parte de las propias CRUD y Paginación por defecto implementadas

    // Todos los productos de un propietario (where propietario..)
    List<Producto> findByPropietario(Usuario propietario);

    // Todos los productos de una compra where compra=...
    List<Producto> findByCompra(Compra compra);

    // Todos los productos no asiciados a una compra (no comprados) where compra=null
    List<Producto> findByCompraIsNull();

    //Todos los productos asicados a un nombre y no comprados: where nombre like... and compra= null
    List<Producto> findByNombreContainsIgnoreCaseAndCompraIsNull(String nombre);

    //Todos los productos de un nombre el cual sea su propietario: where nombre loke and propietario...
    List<Producto> findByNombreContainsIgnoreCaseAndPropietario(String nombre, Usuario propietario);
}
