package be.technifutur.labo3.entity;

import lombok.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import javax.persistence.Embeddable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Embeddable
public class Address {

    String street;
    Integer number;
    String zipCode;
    String city;
    String country;

}
