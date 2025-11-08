package com.joseluisgs.walaspringboot.servicios;

import com.joseluisgs.walaspringboot.modelos.Compra;
import com.joseluisgs.walaspringboot.modelos.Producto;
import com.joseluisgs.walaspringboot.modelos.Usuario;
import com.joseluisgs.walaspringboot.repositorios.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Usamos el Servicio de Comprar para mapear los métdos que vamos a usar, con otro nombre
 * O deseamos llamar de distinta manera a los que hay definidos en el Repositorio
 */
@Service
public class CompraServicio {

    // Respositorio a usar
    @Autowired
    CompraRepository repositorio;

    @Autowired
    ProductoServicio productoServicio;

    //Insertamos una compra indicando el usuario
    public Compra insertar(Compra c, Usuario u) {
        c.setPropietario(u);
        return repositorio.save(c);
    }

    //Insertamos una compra
    public Compra insertar(Compra c) {
        return repositorio.save(c);
    }

    //Añadimos un productoa una compra. Esto es porque un producto solo se puede comprar una vez
    public Producto addProductoCompra(Producto p, Compra c) {
        p.setCompra(c);
        return productoServicio.editar(p);
    }

    //Buscamos una compra por id
    public Compra buscarPorId(long id) {
        return repositorio.findById(id).orElse(null);
    }

    //Listamos todas las compras
    public List<Compra> todas() {
        return repositorio.findAll();
    }

    // Listamos las compras por propietario
    public List<Compra> porPropietario(Usuario u) {
        return repositorio.findByPropietario(u);
    }

}
