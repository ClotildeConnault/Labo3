package be.technifutur.labo3.dto;

import be.technifutur.labo3.entity.AccessLevel;
import be.technifutur.labo3.entity.Address;
import be.technifutur.labo3.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    List<OrderDTO> orders;
}
