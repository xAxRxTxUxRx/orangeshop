package com.artur.orange_backend.model;

import com.artur.orange_backend.model.utils.product.ProductCategory;
import com.artur.orange_backend.model.utils.product.ProductCollection;
import com.artur.orange_backend.model.utils.product.ProductPrice;
import com.artur.orange_backend.model.utils.product.ProductSpecifications;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Blob;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "product_table")
public class Product {
    @Id
    @SequenceGenerator(
            name = "product_id",
            sequenceName = "product_id",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "product_id",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;

    @Enumerated(EnumType.STRING)
    private ProductCategory productCategory;

    @Enumerated(EnumType.STRING)
    private ProductCollection productCollection;

    private String title;

    @Embedded
    private ProductPrice price;

    private List<String> kit = new ArrayList<>();

    private String description;

    @Lob
    private List<Blob> productImages;

    @Lob
    private byte[] assemblyDiagram;

    @ElementCollection
    @CollectionTable(name = "product_advantages",
            joinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "advantages_mapping")
    @Column(name = "advantages")
    private Map<String, String> advantages = new HashMap<>();

    @OneToMany
    @JoinColumn(name = "product_id")
    private List<ProductSpecifications> productSpecifications;
}
