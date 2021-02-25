package be.technifutur.labo3.config;

import be.technifutur.labo3.entity.*;
import be.technifutur.labo3.service.CategoryService;
import be.technifutur.labo3.service.ProductService;
import be.technifutur.labo3.service.SupplierService;
import be.technifutur.labo3.service.UserService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.time.Instant;
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
                    .TVA(6d)
                    .insertDate(Instant.now())
                    .price(2d)
                    .quantity(12)
                    .supplier(suppliers.get(0))
                    .categories(Arrays.asList(categories.get(0)))
                    .build(),
            Product.builder()
                    .name("Kit kat")
                    .TVA(6d)
                    .insertDate(Instant.now())
                    .price(2d)
                    .quantity(20)
                    .supplier(suppliers.get(0))
                    .categories(Arrays.asList(categories.get(0)))
                    .build(),
            Product.builder()
                    .name("Crème au chocolat")
                    .TVA(6d)
                    .insertDate(Instant.now())
                    .price(2d)
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
