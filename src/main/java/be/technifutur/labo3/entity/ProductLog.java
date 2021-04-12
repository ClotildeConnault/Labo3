package be.technifutur.labo3.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class ProductLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    int id;

    @Column(nullable = false)
    int productId;

    @Column(nullable = false, length = 250000)
    String oldProduct;

    @Column(nullable = false, length = 250000)
    String newProduct;

    @Column(nullable = false)
    int userId;

    @CreationTimestamp
    Instant logDate;

}
