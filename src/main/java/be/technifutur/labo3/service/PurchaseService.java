package be.technifutur.labo3.service;

import be.technifutur.labo3.dto.PurchaseDTO;
import be.technifutur.labo3.entity.Purchase;
import be.technifutur.labo3.mapper.Mapper;
import be.technifutur.labo3.repository.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class PurchaseService implements Crudable<Purchase, PurchaseDTO, Integer> {

    private final PurchaseRepository purchaseRepository;
    private final Mapper mapper;

    public PurchaseService(PurchaseRepository purchaseRepository, Mapper mapper) {
        this.purchaseRepository = purchaseRepository;
        this.mapper = mapper;
    }

    @Override
    public List<PurchaseDTO> getAll() {
        return purchaseRepository.findAll()
                .stream()
                .map(mapper::toPurchaseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PurchaseDTO getById(Integer integer) {
        return mapper.toPurchaseDTO(
                purchaseRepository.findById(integer)
                        .orElseThrow(() -> new NoSuchElementException("L'utilisateur à l'ID " + integer + " n'a pas été trouvé"))
        );
    }

    public List<PurchaseDTO> getAllByClient(Integer clientID) {

        List<Purchase> orders =
                purchaseRepository.findAllByUserId(clientID);


             return orders.stream()
                    .map(mapper::toPurchaseDTO)
                     .collect(Collectors.toList());
    }

    @Override
    public boolean insert(Purchase purchase) {
        Purchase o = purchaseRepository.save(purchase);
        return purchaseRepository.existsById(o.getId());
    }

    @Override
    public boolean update(Purchase purchase, Integer integer) {
        Purchase oldPurchase = this.purchaseRepository.getOne(integer);
        Purchase purchaseToTest = new Purchase(
                oldPurchase.getId(),
                oldPurchase.getReference(),
                oldPurchase.getCreationDate(),
                oldPurchase.getProducts(),
                oldPurchase.isPaid(),
                oldPurchase.getPaymentMethod(),
                oldPurchase.getUser()
        );
        purchase.setId(integer);
        this.purchaseRepository.save(purchase);
        return !purchaseToTest.equals(this.purchaseRepository.getOne(integer));
    }

    @Override
    public boolean delete(Integer integer) {
        this.purchaseRepository.deleteById(integer);
        return !this.purchaseRepository.findById(integer).isPresent();
    }
}
