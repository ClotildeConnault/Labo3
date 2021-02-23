package be.technifutur.labo3.dto;

import be.technifutur.labo3.entity.AccessLevel;
import be.technifutur.labo3.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    int id;
    String firstName;
    String lastName;
    AccessLevel accessLevel;
    String pseudo;
    String password;
    Address address;
}
