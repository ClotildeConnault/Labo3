package be.technifutur.labo3.dto;

import be.technifutur.labo3.entity.AccessLevel;
import be.technifutur.labo3.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.openapitools.jackson.nullable.JsonNullable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserUpdateDTO {
    JsonNullable<Integer> id;
    JsonNullable<String> firstName;
    JsonNullable<String> lastName;
    JsonNullable<AccessLevel> accessLevel;
    JsonNullable<String> username;
    JsonNullable<String> password;
    JsonNullable<Address> address;

}
