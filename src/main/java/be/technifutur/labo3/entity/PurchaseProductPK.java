package be.technifutur.labo3.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PurchaseProductPK implements Serializable {

    @Column(name = "purchase_id")
    private Integer purchaseId;

    @Column(name = "product_id")
    private Integer productId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseProductPK that = (PurchaseProductPK) o;
        return Objects.equals(purchaseId, that.purchaseId) &&
                Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(purchaseId, productId);
    }
}
