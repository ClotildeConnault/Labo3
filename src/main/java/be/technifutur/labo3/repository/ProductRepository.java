package be.technifutur.labo3.repository;

import be.technifutur.labo3.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer>, QuerydslPredicateExecutor<Product>, PagingAndSortingRepository<Product, Integer>{
    List<Product> findByInactiveFalse();
    Page<Product> findByInactiveFalse(Pageable var1);
}
