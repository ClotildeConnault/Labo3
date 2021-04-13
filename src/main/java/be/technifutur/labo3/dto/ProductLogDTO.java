package be.technifutur.labo3.dto;

import be.technifutur.labo3.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductLogDTO {

    int id;
    int productId;
    String oldProduct;
    String newProduct;
    int userId;
    Instant logDate;
}
