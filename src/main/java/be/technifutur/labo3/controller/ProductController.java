package be.technifutur.labo3.controller;

import be.technifutur.labo3.dto.ProductDTO;
import be.technifutur.labo3.entity.Product;
import be.technifutur.labo3.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping(params = {"page", "size", "sortingFieldName", "sortingDirection"})
    public ResponseEntity<Page<ProductDTO>> getAllWithPagination(@RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("sortingFieldName") String sortingFieldName, @RequestParam("sortingDirection") String sortingDirection){
        return ResponseEntity.ok((this.service.getAllWithPagination(page,size,sortingFieldName,sortingDirection)));
    }

    @Override
    @GetMapping(path = "/{id}")
    public ResponseEntity<ProductDTO> getOne(@PathVariable("id") Integer integer) {
        return ResponseEntity.ok(service.getById(integer));
    }

    @Override
    @PostMapping
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public ResponseEntity<Boolean> insert(@RequestBody Product product) {
        return ResponseEntity.ok(service.insert(product));
    }

    @Override
    @PutMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRATOR') or hasAuthority('CUSTOMER')")
    public ResponseEntity<Boolean> update(@RequestBody Product product, @PathVariable("id") Integer integer) {
        return ResponseEntity.ok(service.update(product, integer));
    }

    @Override
    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Integer integer) {
        return ResponseEntity.ok(service.delete(integer));
    }

    @PostMapping(path = "/searchByName", params = {"page", "size", "sortingFieldName", "sortingDirection"})
    public ResponseEntity<Page<ProductDTO>> searchByName(@RequestBody String productName, @RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("sortingFieldName") String sortingFieldName, @RequestParam("sortingDirection") String sortingDirection){
        return ResponseEntity.ok(service.searchByProductName(productName,page,size,sortingFieldName,sortingDirection));
    }

    @PostMapping(path = "/search", params = {"page", "size", "sortingFieldName", "sortingDirection"})
    public ResponseEntity<Page<ProductDTO>> search(@RequestBody Product product, @RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("sortingFieldName") String sortingFieldName, @RequestParam("sortingDirection") String sortingDirection){
        return ResponseEntity.ok(service.searchByProduct(product,page,size,sortingFieldName,sortingDirection));
    }
}
