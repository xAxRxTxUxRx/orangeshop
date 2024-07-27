package com.artur.orange_backend.model.utils.product;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class ProductSpecifications {
    @Id
    @SequenceGenerator(
            name = "product_specification_id",
            sequenceName = "product_specification_id",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "product_specification_id",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;

    private String title;
    private String value;

    public ProductSpecifications(String title,
                                 String value) {
        this.title = title;
        this.value = value;
    }
}
