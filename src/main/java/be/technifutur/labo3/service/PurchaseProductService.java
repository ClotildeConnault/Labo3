package be.technifutur.labo3.service;

import be.technifutur.labo3.dto.PurchaseProductDTO;
import be.technifutur.labo3.entity.PurchaseProduct;
import be.technifutur.labo3.entity.PurchaseProductPK;
import be.technifutur.labo3.repository.PurchaseProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseProductService implements Crudable<PurchaseProduct, PurchaseProductDTO, PurchaseProductPK> {

    private final PurchaseProductRepository repository;

    public PurchaseProductService(PurchaseProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<PurchaseProductDTO> getAll() {
        return null;
    }

    @Override
    public PurchaseProductDTO getById(PurchaseProductPK purchaseProductPK) {
        return null;
    }

    @Override
    public boolean insert(PurchaseProduct purchaseProduct) {
        this.repository.save(purchaseProduct);
        return true;
    }

    @Override
    public boolean update(PurchaseProduct purchaseProduct, PurchaseProductPK purchaseProductPK) {
        return false;
    }

    @Override
    public boolean delete(PurchaseProductPK purchaseProductPK) {
        return false;
    }
}
