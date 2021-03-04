package be.technifutur.labo3.controller;

import be.technifutur.labo3.dto.PurchaseDTO;
import be.technifutur.labo3.entity.Purchase;
import be.technifutur.labo3.service.PurchaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/orders")
public class PurchaseController implements RestControllable<Purchase, PurchaseDTO, Integer> {

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<PurchaseDTO>> getAll() {
        return ResponseEntity.ok(purchaseService.getAll());
    }

    @Override
    @GetMapping(path = "/{id}")
    public ResponseEntity<PurchaseDTO> getOne(@PathVariable("id") Integer integer) {
        return ResponseEntity.ok(purchaseService.getById(integer));
    }

    @GetMapping(path = "/{idClient}/orders")
    public ResponseEntity<List<PurchaseDTO>> getAllByClient(@PathVariable("idClient") Integer idClient) {
        return ResponseEntity.ok(purchaseService.getAllByClient(idClient));
    }

    @Override
    @PostMapping
    public ResponseEntity<Boolean> insert(@RequestBody Purchase purchase) {
        System.out.println("purchaseController");
        return ResponseEntity.ok(purchaseService.insert(purchase));
    }

    @Override
    @PutMapping(path = "/{id}")
    public ResponseEntity<Boolean> update(@RequestBody Purchase purchase, @PathVariable("id") Integer integer) {
        return ResponseEntity.ok(purchaseService.update(purchase, integer));
    }

    @Override
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Integer integer) {
        return ResponseEntity.ok(purchaseService.delete(integer));
    }
}
