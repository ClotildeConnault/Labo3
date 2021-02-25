package be.technifutur.labo3.service;

import be.technifutur.labo3.dto.ProductDTO;
import be.technifutur.labo3.entity.Category;
import be.technifutur.labo3.entity.Product;
import be.technifutur.labo3.entity.QProduct;
import be.technifutur.labo3.mapper.Mapper;
import be.technifutur.labo3.repository.ProductRepository;
import com.querydsl.core.BooleanBuilder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

    public List<ProductDTO> searchByProductName(String productName){

        BooleanBuilder predicate = new BooleanBuilder();

        QProduct qProduct = QProduct.product;

        if(productName != null && !productName.equals("")){
            predicate.and(qProduct.name.containsIgnoreCase(productName));
        }

        Iterable<Product> result = this.productRepository.findAll(predicate);

        return StreamSupport.stream(result.spliterator(),false)
                .map(mapper::toProductDTO)
                .collect(Collectors.toList());
    }
}
