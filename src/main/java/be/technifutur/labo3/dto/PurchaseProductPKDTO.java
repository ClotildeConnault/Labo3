package be.technifutur.labo3.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseProductPKDTO {
    private Integer purchaseId;

    private Integer productId;
}
