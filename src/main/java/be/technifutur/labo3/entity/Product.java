package be.technifutur.labo3.entity;

import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @CreationTimestamp
    private Instant insertDate;

    @UpdateTimestamp
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

}
