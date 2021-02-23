package be.technifutur.labo3.repository;

import be.technifutur.labo3.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
