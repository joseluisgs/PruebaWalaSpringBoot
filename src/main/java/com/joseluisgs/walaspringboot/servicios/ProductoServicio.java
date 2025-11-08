package com.joseluisgs.walaspringboot.servicios;

import com.joseluisgs.walaspringboot.modelos.Compra;
import com.joseluisgs.walaspringboot.modelos.Producto;
import com.joseluisgs.walaspringboot.modelos.Usuario;
import com.joseluisgs.walaspringboot.repositorios.ProductoRepository;
import com.joseluisgs.walaspringboot.upload.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Usamos el Servicio de Producto para mapear los métdos que vamos a usar, con otro nombre
 * O deseamos llamar de distinta manera a los que hay definidos en el Repositorio
 */
@Service
public class ProductoServicio {
    // Respostorio a usar
    @Autowired
    ProductoRepository repositorio;

    @Autowired
    StorageService storageService;

    // Insertar un producto
    public Producto insertar(Producto p) {
        return repositorio.save(p);
    }

    // Borrar un producto por sud id
    public void borrar(long id) {
        repositorio.deleteById(id);
    }

    // Borrar un producto
    public void borrar(Producto p) {
        if (!p.getImagen().isEmpty())
             storageService.delete(p.getImagen());
        repositorio.delete(p);
    }

    // Editar un producto
    public Producto editar(Producto p) {
        return repositorio.save(p);
    }

    //Encontrar un producto por su id
    public Producto findById(long id) {
        return repositorio.findById(id).orElse(null);
    }

    // Lista todos los productos
    public List<Producto> findAll() {
        return repositorio.findAll();
    }

    // Listar todos los productos de un usuario
    public List<Producto> productosDeUnPropietario(Usuario u) {
        return repositorio.findByPropietario(u);
    }

    // Listar todos los productos de una compra
    public List<Producto> productosDeUnaCompra(Compra c) {
        return repositorio.findByCompra(c);
    }

    // Listar todos los productos sin vender
    public List<Producto> productosSinVender() {
        return repositorio.findByCompraIsNull();
    }

    // Buscar todos los productos
    public List<Producto> buscar(String query) {
        return repositorio.findByNombreContainsIgnoreCaseAndCompraIsNull(query);
    }

    // Buscar los productos de un usuario
    public List<Producto> buscarMisProductos(String query, Usuario u) {
        return repositorio.findByNombreContainsIgnoreCaseAndPropietario(query,u);
    }

    // Listar todos los productos dados ids
    public List<Producto> variosPorId(List<Long> ids) {
        return repositorio.findAllById(ids);
    }
}
