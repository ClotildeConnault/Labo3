package be.technifutur.labo3.repository;

import be.technifutur.labo3.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
