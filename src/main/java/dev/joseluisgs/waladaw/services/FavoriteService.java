package dev.joseluisgs.waladaw.services;

import dev.joseluisgs.waladaw.models.Favorite;
import dev.joseluisgs.waladaw.models.Product;
import dev.joseluisgs.waladaw.models.User;
import dev.joseluisgs.waladaw.repositories.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private ProductService productService;

    public List<Favorite> findByUsuario(User usuario) {
        return favoriteRepository.findByUsuario(usuario);
    }

    public List<Product> getFavoriteProducts(User usuario) {
        return findByUsuario(usuario).stream()
                .map(Favorite::getProducto)
                .collect(Collectors.toList());
    }

    public boolean isFavorite(User usuario, Long productoId) {
        return favoriteRepository.existsByUsuarioAndProductoId(usuario, productoId);
    }

    public Favorite addFavorite(User usuario, Long productoId) {
        if (!isFavorite(usuario, productoId)) {
            Product producto = productService.findById(productoId);
            if (producto != null) {
                Favorite favorite = new Favorite(usuario, producto);
                return favoriteRepository.save(favorite);
            }
        }
        return null;
    }

    @Transactional
    public void removeFavorite(User usuario, Long productoId) {
        favoriteRepository.deleteByUsuarioAndProductoId(usuario, productoId);
    }

    public int contarFavoritos(User usuario) {
        return findByUsuario(usuario).size();
    }
}
