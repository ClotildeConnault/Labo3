package be.technifutur.labo3.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Data
@Entity
@Table(name = "PurchaseProduct")
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseProduct {

    @EmbeddedId
    private PurchaseProductPK id;


    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @ManyToOne
    @MapsId("purchaseId")
    @JoinColumn(name = "PURCHASE_ID")
    private Purchase purchase;

    @Column
    private Integer quantity;
}
