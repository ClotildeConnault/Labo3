package be.technifutur.labo3.dto;

import be.technifutur.labo3.entity.Product;
import be.technifutur.labo3.entity.Purchase;
import be.technifutur.labo3.entity.PurchaseProductPK;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseProductDTO {

    private PurchaseProductPKDTO id;

    private ProductDTO product;

    private PurchaseDTO purchase;

    private Integer quantity;
}
