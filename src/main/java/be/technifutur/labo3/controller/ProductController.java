package be.technifutur.labo3.controller;

import be.technifutur.labo3.dto.ProductDTO;
import be.technifutur.labo3.entity.Product;
import be.technifutur.labo3.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/products")
public class ProductController implements RestControllable<Product, ProductDTO, Integer> {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @Override
    @GetMapping(path = "/{id}")
    public ResponseEntity<ProductDTO> getOne(@PathVariable("id") Integer integer) {
        return ResponseEntity.ok(service.getById(integer));
    }

    @Override
    @PostMapping
    public ResponseEntity<Boolean> insert(@RequestBody Product product) {
        return ResponseEntity.ok(service.insert(product));
    }

    @Override
    @PutMapping(path = "/{id}")
    public ResponseEntity<Boolean> update(@RequestBody Product product, @PathVariable("id") Integer integer) {
        return ResponseEntity.ok(service.update(product, integer));
    }

    @Override
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Integer integer) {
        return ResponseEntity.ok(service.delete(integer));
    }

    @GetMapping(path = "/searchByName")
    public ResponseEntity<List<ProductDTO>> searchByName(@RequestBody String productName){
        return ResponseEntity.ok(service.searchByProductName(productName));
    }

    @GetMapping(path = "/search")
    public ResponseEntity<List<ProductDTO>> search(@RequestBody Product product){
        return ResponseEntity.ok(service.searchByProduct(product));
    }
}
