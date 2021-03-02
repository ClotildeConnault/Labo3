package be.technifutur.labo3.repository;

import be.technifutur.labo3.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {

    @Query(value = "SELECT * FROM PURCHASE WHERE USER_ID = 1", nativeQuery = true)
    List<Purchase> findAllByUserId(Integer id);
}
