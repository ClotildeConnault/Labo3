package be.technifutur.labo3.mapper;

import be.technifutur.labo3.dto.ProductDTO;
import be.technifutur.labo3.entity.Product;
import org.springframework.stereotype.Service;

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

}
