package com.artur.orange_backend.dto;

import com.artur.orange_backend.model.utils.product.ProductCategory;
import com.artur.orange_backend.model.utils.product.ProductCollection;
import com.artur.orange_backend.model.utils.product.ProductPrice;
import com.artur.orange_backend.model.utils.product.ProductSpecifications;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductViewDTO {
    private Long id;

    private ProductCategory productCategory;

    private ProductCollection productCollection;

    private String title;

    @Embedded
    private ProductPrice price;

    private List<String> kit = new ArrayList<>();

    private String description;

    private List<Blob> productImages;

    private byte[] assemblyDiagram;

    private Map<String, String> advantages = new HashMap<>();

    private List<ProductSpecifications> productSpecifications;
}
