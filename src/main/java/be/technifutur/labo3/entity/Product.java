package be.technifutur.labo3.entity;

import com.opencsv.bean.CsvBindByName;
import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Value;


import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
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

    @Column(columnDefinition = "varchar(255) default 'https://www.kabylieclim.com/wp-content/uploads/2015/09/Pas-dimage-disponible.jpg;'")
    private String imagePath;

    @Column(nullable = false)
    private double tva;

    @ManyToMany
    private List<Category> categories;

    @ManyToOne
    private Supplier supplier;

    @Column
    private boolean inactive;

    @OneToMany(mappedBy = "purchase")
    private List<PurchaseProduct> purchaseProducts = new ArrayList<PurchaseProduct>();


}
