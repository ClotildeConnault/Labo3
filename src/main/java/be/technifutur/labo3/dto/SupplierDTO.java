package be.technifutur.labo3.dto;

import be.technifutur.labo3.entity.Product;
import be.technifutur.labo3.entity.Sector;
import be.technifutur.labo3.entity.SocialStatut;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupplierDTO {

    private int id;
    private String companyName;
    private SocialStatut statut;
    private Sector sector;
    private Instant insertionDate;
    private Instant updateDate;
    private List<ProductDTO> products;
    private boolean inactive;
}
