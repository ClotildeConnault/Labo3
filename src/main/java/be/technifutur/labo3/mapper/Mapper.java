package be.technifutur.labo3.mapper;

import be.technifutur.labo3.dto.ProductDTO;
import be.technifutur.labo3.dto.SupplierDTO;
import be.technifutur.labo3.entity.Product;
import be.technifutur.labo3.entity.Supplier;
import be.technifutur.labo3.dto.UserDTO;
import be.technifutur.labo3.entity.Product;
import be.technifutur.labo3.entity.User;
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






























    public UserDTO toUserDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .accessLevel(user.getAccessLevel())
                .pseudo(user.getPseudo())
                .password(user.getPassword())
                .address(user.getAddress())
                .build();
    }

    public User toUserEntity(UserDTO userDTO) {
        return User.builder()
                .id(userDTO.getId())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .accessLevel(userDTO.getAccessLevel())
                .pseudo(userDTO.getPseudo())
                .password(userDTO.getPassword())
                .address(userDTO.getAddress())
                .build();
    }

}
