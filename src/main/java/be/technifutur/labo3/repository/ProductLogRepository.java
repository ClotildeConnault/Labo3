package be.technifutur.labo3.repository;

import be.technifutur.labo3.entity.ProductLog;
import be.technifutur.labo3.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductLogRepository extends JpaRepository<ProductLog, Integer> {
    Optional<List<ProductLog>> findByProductId(int id);
    Optional<List<ProductLog>> findByUserId(int id);
}
