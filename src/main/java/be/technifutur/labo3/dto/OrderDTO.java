package be.technifutur.labo3.dto;

import be.technifutur.labo3.entity.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO {
    int id;
    String reference;
    Instant creationDate;
    List<ProductDTO> products;
    boolean isPaid;
    PaymentMethod paymentMethod;
    UserDTO user;
}
