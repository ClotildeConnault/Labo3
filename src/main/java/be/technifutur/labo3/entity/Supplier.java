package be.technifutur.labo3.entity;

import lombok.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.time.Instant;
import java.util.List;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column
    String companyName;

    @Enumerated(EnumType.STRING)
    SocialStatut statut;

    @Enumerated(EnumType.STRING)
    Sector sector;

    @CreationTimestamp
    Instant insertionDate;

    @UpdateTimestamp
    Instant updateDate;

    @OneToMany
    List<Product> products;
}