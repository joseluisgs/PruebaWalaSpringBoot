package com.joseluisgs.walaspringboot.repositories;

import com.joseluisgs.walaspringboot.models.Product;
import com.joseluisgs.walaspringboot.models.Purchase;
import com.joseluisgs.walaspringboot.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> findByPropietario(User propietario);
    
    @Query("SELECT COUNT(c) FROM Purchase c WHERE c.propietario = :propietario")
    long countByPropietario(@Param("propietario") User propietario);
    
    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Product p WHERE p.compra IS NOT NULL AND p = :product")
    boolean existsByProduct(@Param("product") Product product);
}
