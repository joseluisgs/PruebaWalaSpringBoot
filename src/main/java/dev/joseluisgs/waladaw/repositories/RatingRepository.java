package dev.joseluisgs.waladaw.repositories;

import dev.joseluisgs.waladaw.models.Product;
import dev.joseluisgs.waladaw.models.Rating;
import dev.joseluisgs.waladaw.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByProducto(Product producto);

    Optional<Rating> findByUsuarioAndProducto(User usuario, Product producto);

    @Query("SELECT AVG(r.puntuacion) FROM Rating r WHERE r.producto = ?1")
    Double getAverageRatingByProducto(Product producto);

    @Query("SELECT COUNT(r) FROM Rating r WHERE r.producto = ?1")
    Long getCountByProducto(Product producto);
}
