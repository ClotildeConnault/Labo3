package be.technifutur.labo3.dto;

import be.technifutur.labo3.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {

    private int id;

    private String name;

    private String description;

    private Instant insertDate;

    private Instant updateDate;

    private Instant expirationDate;

    private double price;

    private Integer quantity;

    private String imagePath;

    private double tva;

    private List<CategoryDTO> categories;

    private SupplierDTO supplier;
}
