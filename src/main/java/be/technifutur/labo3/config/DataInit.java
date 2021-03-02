package be.technifutur.labo3.config;

import be.technifutur.labo3.entity.*;
import be.technifutur.labo3.service.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Service
public class DataInit implements InitializingBean {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final UserService userService;
    private final SupplierService supplierService;
    private final PurchaseService purchaseService;


    private List<Category> categories = Arrays.asList(
            Category.builder()
                    .name("Produits laitiers")
                    .build(),

            Category.builder()
                    .name("Céréales")
                    .build(),

            Category.builder()
                    .name("Friandises")
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

    /*private List<Product> products = Arrays.asList(
            Product.builder()
                    .name("Yaourt")
                    .description("Ceci est un yaourt")
                    .expirationDate(Instant.ofEpochSecond(LocalDateTime.of(2021,02,25,0,1).toEpochSecond(ZoneOffset.ofHours(0))))
                    .tva(6d)
                    .insertDate(Instant.now())
                    .price(2.0)
                    .quantity(12)
                    .imagePath("https://cdn-s-www.ledauphine.com/images/95A10B41-F408-4B27-8E65-2E89F04ECF54/NW_raw/illustration-1583764974.jpg")
                    .supplier(suppliers.get(1))
                    .categories(Arrays.asList(categories.get(0)))
                    .build(),
            Product.builder()
                    .name("Kit kat")
                    .description("Ceci est un kit kat")
                    .expirationDate(Instant.ofEpochSecond(LocalDateTime.of(2021,03,25,0,1).toEpochSecond(ZoneOffset.ofHours(0))))
                    .tva(6d)
                    .insertDate(Instant.now())
                    .price(2.5)
                    .quantity(20)
                    .supplier(suppliers.get(0))
                    .categories(Arrays.asList(categories.get(2)))
                    .build(),
            Product.builder()
                    .name("Crème au chocolat")
                    .description("Ceci est une crème au chocolat")
                    .expirationDate(Instant.ofEpochSecond(LocalDateTime.of(2021,04,25,0,1).toEpochSecond(ZoneOffset.ofHours(0))))
                    .tva(6d)
                    .insertDate(Instant.now())
                    .price(1.48)
                    .quantity(15)
                    .supplier(suppliers.get(0))
                    .categories(Arrays.asList(categories.get(0)))
                    .build()

    );*/

    List<Product> products = new ArrayList<>();
    {
        Scanner sc = new Scanner(new File("src/dataProduct.csv"));

        while(sc.hasNext()){
            String line = sc.nextLine();
            String[] element = concatProductName(line.split(","));
            products.add(Product.builder()
                    .name(element[0])
                    .description("This is a " + element[0])
                    .expirationDate(Instant.ofEpochSecond(Long.parseLong(element[1])))
                    .tva(Double.parseDouble(element[2]))
                    .price(Double.parseDouble(element[3]))
                    .quantity(Integer.parseInt(element[4]))
                    .imagePath(element[5])
                    .supplier(suppliers.get(0))
                    .categories(Arrays.asList(categories.get(0)))
                    .build());
        }
    }

    public String[] concatProductName (String[] element){
        String[] ret = new String[6];

        ret[0]= element[0];
        for(int i=1; i<= element.length-6;i++){
            ret[0] += "," + element[i];
        }
        ret[1] = element[element.length-5];
        ret[2] = element[element.length-4];
        ret[3] = element[element.length-3];
        ret[4] = element[element.length-2];
        ret[5] = element[element.length-1];

        return ret;
    }




    private List<User> users = Arrays.asList(
            User.builder()
                    .firstName("Clotilde")
                    .lastName("Connault")
                    .address(new Address("Rue du Faubourg Saint-Honoré",55,"75008","Paris 8e","France"))
                    .accessLevel(AccessLevel.ADMINISTRATOR)
                    .pseudo("Clo")
                    .password("3LYS33")
                    .build(),
            User.builder()
                    .firstName("Benjamin")
                    .lastName("Valin")
                    .address(new Address("Rue Gaston Grégoire",17,"4540","Amay","Belgique"))
                    .accessLevel(AccessLevel.ADMINISTRATOR)
                    .pseudo("Chbench")
                    .password("CH83NCH")
                    .build(),
            User.builder()
                    .firstName("Stephan")
                    .lastName("Carion")
                    .address(new Address("Rue du Grand Bleu",30,"4000","Liège","Belgique"))
                    .accessLevel(AccessLevel.ADMINISTRATOR)
                    .pseudo("Steph")
                    .password("PL0NG33")
                    .build()
    );

    private List<Purchase> purchases = Arrays.asList(
            Purchase.builder()
                    .reference("ORD000001")
                    .creationDate(Instant.now())
                    .products(Arrays.asList(products.get(0), (products.get(1))))
                    .isPaid(true)
                    .paymentMethod(PaymentMethod.PAYPAL)
                    .user(users.get(0))
                    .build(),
            Purchase.builder()
                    .reference("ORD000004")
                    .creationDate(Instant.now())
                    .products(Arrays.asList(products.get(5), (products.get(20))))
                    .isPaid(true)
                    .paymentMethod(PaymentMethod.PAYPAL)
                    .user(users.get(1))
                    .build(),
            Purchase.builder()
                    .reference("ORD000002")
                    .creationDate(Instant.now())
                    .products(Arrays.asList(products.get(1), (products.get(2))))
                    .isPaid(true)
                    .paymentMethod(PaymentMethod.CASH)
                    .user(users.get(2))
                    .build(),
            Purchase.builder()
                    .reference("ORD000003")
                    .creationDate(Instant.now())
                    .products(Arrays.asList(products.get(0), (products.get(2))))
                    .isPaid(true)
                    .paymentMethod(PaymentMethod.CREDIT_CARD)
                    .user(users.get(0))
                    .build()
    );

    public DataInit(ProductService productService, CategoryService categoryService, UserService userService, SupplierService supplierService, PurchaseService purchaseService) throws FileNotFoundException {
        this.productService = productService;
        this.categoryService = categoryService;
        this.userService = userService;
        this.supplierService = supplierService;
        this.purchaseService = purchaseService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        categories.forEach(categoryService::insert);
        suppliers.forEach(supplierService::insert);
        products.forEach(productService::insert);
		products.forEach(p -> System.out.println(p.getImagePath()));
		users.forEach(userService::insert);
        purchases.forEach(purchaseService::insert);
    }
}
