package be.technifutur.labo3.mapper;

import be.technifutur.labo3.dto.ProductDTO;
import be.technifutur.labo3.dto.SupplierDTO;
import be.technifutur.labo3.entity.Product;
import be.technifutur.labo3.entity.Supplier;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class Mapper {

    public ProductDTO toProductDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .categories(product.getCategories())
                .description(product.getDescription())
                .expirationDate(product.getExpirationDate())
                .imagePath(product.getImagePath())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .updateDate(product.getUpdateDate())
                .insertDate(product.getInsertDate())
                .TVA(product.getTVA())
                .build();
    }

    public Product toProductEntity(ProductDTO product) {
        return Product.builder()
                .id(product.getId())
                .name(product.getName())
                .categories(product.getCategories())
                .description(product.getDescription())
                .expirationDate(product.getExpirationDate())
                .imagePath(product.getImagePath())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .updateDate(product.getUpdateDate())
                .insertDate(product.getInsertDate())
                .TVA(product.getTVA())
                .build();
    }

    public SupplierDTO toSupplierDTO(Supplier supplier){
        return SupplierDTO.builder()
                .id(supplier.getId())
                .companyName(supplier.getCompanyName())
                .statut(supplier.getStatut())
                .sector(supplier.getSector())
                .insertionDate(supplier.getInsertionDate())
                .updateDate(supplier.getUpdateDate())
                .products(supplier.getProducts()
                        .stream()
                        .map(this::toProductDTO)
                        .collect(Collectors.toList())
                )
                .build();
    }

    public Supplier toSupplierEntity(SupplierDTO supplierDTO){
        return Supplier.builder()
                .id(supplierDTO.getId())
                .companyName(supplierDTO.getCompanyName())
                .statut(supplierDTO.getStatut())
                .sector(supplierDTO.getSector())
                .insertionDate(supplierDTO.getInsertionDate())
                .updateDate(supplierDTO.getUpdateDate())
                .products(supplierDTO.getProducts()
                            .stream()
                            .map(this::toProductEntity)
                            .collect(Collectors.toList())
                )
                .build();
    }
}
