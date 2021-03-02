package be.technifutur.labo3.mapper;

import be.technifutur.labo3.dto.*;
import be.technifutur.labo3.entity.*;
import be.technifutur.labo3.entity.Product;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class Mapper {

    public CategoryDTO toCategoryDTO(Category category) {
        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    public Category toCategoryEntity(CategoryDTO category) {
        return Category.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    public ProductDTO toProductDTO(Product product) {
         ProductDTO dto = ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .categories(product.getCategories()
                                    .stream()
                                    .map(this::toCategoryDTO)
                                    .collect(Collectors.toList())
                )
                .description(product.getDescription())
                .expirationDate(product.getExpirationDate())
                .imagePath(product.getImagePath())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .updateDate(product.getUpdateDate())
                .insertDate(product.getInsertDate())
                .tva(product.getTva())
                .supplier(product.getSupplier() != null? toSupplierDTO(product.getSupplier()) : null)
                .inactive(product.isInactive())
                .tva(product.getTva())
                .supplier(toSupplierDTO(product.getSupplier()))
                .build();

        System.out.println(dto.isInactive());
        return dto;
    }

    public Product toProductEntity(ProductDTO product) {
        Product productTest = Product.builder()
                .id(product.getId())
                .name(product.getName())
                .categories(product.getCategories()
                        .stream()
                        .map(this::toCategoryEntity)
                        .collect(Collectors.toList()))
                .description(product.getDescription())
                .expirationDate(product.getExpirationDate())
                .imagePath(product.getImagePath())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .updateDate(product.getUpdateDate())
                .insertDate(product.getInsertDate())
                .tva(product.getTva())
                .supplier(toSupplierEntity(product.getSupplier()))
                .inactive(product.isInactive())
                .build();

        System.out.println(productTest.isInactive());
        return productTest;
    }

    public SupplierDTO toSupplierDTO(Supplier supplier) {
        return toSupplierDTO(supplier, false);
    }

    public SupplierDTO toSupplierDTO (Supplier supplier, boolean withProducts){
        return SupplierDTO.builder()
                .id(supplier.getId())
                .companyName(supplier.getCompanyName())
                .statut(supplier.getStatut())
                .sector(supplier.getSector())
                .insertionDate(supplier.getInsertionDate())
                .updateDate(supplier.getUpdateDate())
                .products(withProducts? supplier.getProducts()
                                .stream()
                                .map(this::toProductDTO)
                                .collect(Collectors.toList())
                : null)
                .inactive(supplier.isInactive())
                .build();
    }

    public Supplier toSupplierEntity (SupplierDTO dto){
        return Supplier.builder()
                .id(dto.getId())
                .companyName(dto.getCompanyName())
                .statut(dto.getStatut())
                .sector(dto.getSector())
                .insertionDate(dto.getInsertionDate())
                .updateDate(dto.getUpdateDate())
                .products(dto.getProducts()
                        .stream()
                        .map(this::toProductEntity)
                        .collect(Collectors.toList()))
                .inactive(dto.isInactive())
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
                .orders(user.getOrders()
                        .stream()
                        .map(this::toOrderDTO)
                        .collect(Collectors.toList())
                )
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
                .orders(userDTO.getOrders()
                        .stream()
                        .map(this::toOrderEntity)
                        .collect(Collectors.toList())
                )
                .build();
    }

    public OrderDTO toOrderDTO(Order order) {
        return OrderDTO.builder()
                .id(order.getId())
                .reference(order.getReference())
                .creationDate(order.getCreationDate())
                .products(order.getProducts()
                        .stream()
                        .map(this::toProductDTO)
                        .collect(Collectors.toList())
                )
                .isPaid(order.isPaid())
                .paymentMethod(order.getPaymentMethod())
                .user(toUserDTO(order.getUser()))
                .build();
    }

    public Order toOrderEntity(OrderDTO order) {
        return Order.builder()
                .id(order.getId())
                .reference(order.getReference())
                .creationDate(order.getCreationDate())
                .products(order.getProducts()
                        .stream()
                        .map(this::toProductEntity)
                        .collect(Collectors.toList())
                )
                .isPaid(order.isPaid())
                .paymentMethod(order.getPaymentMethod())
                .user(toUserEntity(order.getUser()))
                .build();
    }

}
