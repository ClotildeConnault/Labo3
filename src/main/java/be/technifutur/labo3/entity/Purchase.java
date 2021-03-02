package be.technifutur.labo3.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@Builder
@Table(name = "purchase")
@Entity
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column
    String reference;

    @CreationTimestamp
    Instant creationDate;

    @ManyToMany
    List<Product> products;

    @Column
    boolean isPaid;

    @Column
    PaymentMethod paymentMethod;

    @ManyToOne
    User user;
}
