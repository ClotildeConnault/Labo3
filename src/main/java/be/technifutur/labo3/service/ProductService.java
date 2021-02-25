package be.technifutur.labo3.service;

import be.technifutur.labo3.dto.ProductDTO;
import be.technifutur.labo3.entity.Category;
import be.technifutur.labo3.entity.Product;
import be.technifutur.labo3.mapper.Mapper;
import be.technifutur.labo3.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ProductService implements Crudable<Product, ProductDTO, Integer> {

    private final ProductRepository productRepository;
    private final Mapper mapper;

    public ProductService(ProductRepository productRepository, Mapper mapper) {
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    @Override
    public List<ProductDTO> getAll() {
        return productRepository.findAll()
                .stream()
                .map(mapper::toProductDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO getById(Integer integer) {
        return mapper.toProductDTO(
                productRepository.findById(integer)
                        .orElseThrow(() -> new NoSuchElementException())
        );
    }

    @Override
    public boolean insert(Product product) {
        Product p = productRepository.save(product);
        return productRepository.existsById(p.getId());
    }
    private int id;

    private String name;

    private String description;

    private Instant insertDate;

    private Instant updateDate;

    private Instant expirationDate;

    private double price;

    private Integer quantity;

    private String imagePath;

    private double TVA;

    private List<Category> categories;

    @Override
    public boolean update(Product product, Integer integer) {
        Product old = productRepository.getOne(integer);
        Product toTest = new Product(
                old.getId(),
                old.getName(),
                old.getDescription(),
                old.getInsertDate(),
                old.getUpdateDate(),
                old.getExpirationDate(),
                old.getPrice(),
                old.getQuantity(),
                old.getImagePath(),
                old.getTVA(),
                old.getCategories(),
                old.getSupplier()
        );
        product.setId(integer);
        product.setInsertDate(toTest.getInsertDate());
        productRepository.save(product);
        return !toTest.equals(productRepository.getOne(integer));
    }

    @Override
    public boolean delete(Integer integer) {
        productRepository.deleteById(integer);
        return !productRepository.existsById(integer);
    }
}
