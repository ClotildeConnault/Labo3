package be.technifutur.labo3.entity;

import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Product implements Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column(nullable = false)
    private Instant insertDate;

    @Column
    private Instant updateDate;

    @Column
    private Instant expirationDate;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private Integer quantity;

    @Column
    private String imagePath;

    @Column(nullable = false)
    private double TVA;

    @ManyToMany
    private List<Category> categories;

    @ManyToOne
    private Supplier supplier;

   /* @Override
    protected Product clone() throws CloneNotSupportedException {

        // Assign the shallow copy to
        // new reference variable t
        Product p = (Product)super.clone();

        // Creating a deep copy for c

        t.c.x = c.x;
        t.c.y = c.y;

        // Create a new object for the field c
        // and assign it to shallow copy obtained,
        // to make it a deep copy
        return t;
    }*/
}
