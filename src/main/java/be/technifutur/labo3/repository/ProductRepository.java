package be.technifutur.labo3.repository;

import be.technifutur.labo3.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepository extends JpaRepository<Product, Integer>, QuerydslPredicateExecutor<Product>, PagingAndSortingRepository<Product, Integer>{
}
