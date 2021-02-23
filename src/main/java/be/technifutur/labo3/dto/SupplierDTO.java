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


    int id;
    String companyName;
    SocialStatut statut;
    Sector sector;
    Instant insertionDate;
    Instant updateDate;
    List<ProductDTO> products;
}
