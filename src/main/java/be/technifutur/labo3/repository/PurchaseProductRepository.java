package be.technifutur.labo3.repository;

import be.technifutur.labo3.entity.PurchaseProduct;
import be.technifutur.labo3.entity.PurchaseProductPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseProductRepository extends JpaRepository<PurchaseProduct, PurchaseProductPK> {
}
