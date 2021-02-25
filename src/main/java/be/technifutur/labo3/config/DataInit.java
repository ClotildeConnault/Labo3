package be.technifutur.labo3.config;

import be.technifutur.labo3.entity.*;
import be.technifutur.labo3.service.CategoryService;
import be.technifutur.labo3.service.ProductService;
import be.technifutur.labo3.service.SupplierService;
import be.technifutur.labo3.service.UserService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.Arrays;
import java.util.List;

@Service
public class DataInit implements InitializingBean {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final UserService userService;
    private final SupplierService supplierService;


    private List<Category> categories = Arrays.asList(
            Category.builder()
            .name("produits laitiers")
            .build(),

            Category.builder()
                    .name("céréales")
                    .build()
    );

    private List<Supplier> suppliers = Arrays.asList(
            Supplier.builder()
                    .companyName("Nestlé")
                    .insertionDate(Instant.now())
                    .sector(Sector.ALIMENTATION)
                    .statut(SocialStatut.SA)
                    .build(),

            Supplier.builder()
                    .companyName("Danone")
                    .insertionDate(Instant.now())
                    .sector(Sector.ALIMENTATION)
                    .statut(SocialStatut.SA)
                    .build()

    );

    private List<Product> products = Arrays.asList(
            Product.builder()
                    .name("yaourt")
                    .description("Ceci est un yaourt")
                    .expirationDate(Instant.ofEpochSecond(LocalDateTime.of(2021,02,25,0,1).toEpochSecond(ZoneOffset.ofHours(0))))
                    .TVA(6d)
                    .insertDate(Instant.now())
                    .price(2.0)
                    .quantity(12)
                    .imagePath("https://cdn-s-www.ledauphine.com/images/95A10B41-F408-4B27-8E65-2E89F04ECF54/NW_raw/illustration-1583764974.jpg")
                    .supplier(suppliers.get(0))
                    .categories(Arrays.asList(categories.get(0)))
                    .build(),
            Product.builder()
                    .name("Kit kat")
                    .description("Ceci est un kit kat")
                    .expirationDate(Instant.ofEpochSecond(LocalDateTime.of(2021,03,25,0,1).toEpochSecond(ZoneOffset.ofHours(0))))
                    .TVA(6d)
                    .insertDate(Instant.now())
                    .price(2.5)
                    .quantity(20)
                    .supplier(suppliers.get(0))
                    .categories(Arrays.asList(categories.get(0)))
                    .build(),
            Product.builder()
                    .name("Crème au chocolat")
                    .description("Ceci est une crème au chocolat")
                    .expirationDate(Instant.ofEpochSecond(LocalDateTime.of(2021,04,25,0,1).toEpochSecond(ZoneOffset.ofHours(0))))
                    .TVA(6d)
                    .insertDate(Instant.now())
                    .price(1.48)
                    .quantity(15)
                    .supplier(suppliers.get(0))
                    .categories(Arrays.asList(categories.get(0)))
                    .build()

    );

    public DataInit(ProductService productService, CategoryService categoryService, UserService userService, SupplierService supplierService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.userService = userService;
        this.supplierService = supplierService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        categories.forEach(categoryService::insert);
        suppliers.forEach(supplierService::insert);
        products.forEach(productService::insert);
    }
}
