package be.technifutur.labo3.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@Builder
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(nullable = false)
    String firstName;

    @Column(nullable = false)
    String lastName;

    @Column(nullable = false)
    AccessLevel accessLevel;

    @Column(nullable = false)
    String pseudo;

    @Column(nullable = false)
    String password;

    @Embedded
    Address address;

}
