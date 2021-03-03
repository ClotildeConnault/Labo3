package be.technifutur.labo3.dto;

import be.technifutur.labo3.entity.PaymentMethod;
import be.technifutur.labo3.entity.PurchaseProduct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseDTO {
    int id;
    String reference;
    Instant creationDate;
    List<PurchaseProductDTO> purchaseProducts = new ArrayList<PurchaseProductDTO>();
    boolean isPaid;
    PaymentMethod paymentMethod;
    UserDTO user;
}
