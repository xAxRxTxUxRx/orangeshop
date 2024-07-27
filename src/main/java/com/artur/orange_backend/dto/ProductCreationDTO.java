package com.artur.orange_backend.dto;

import com.artur.orange_backend.model.utils.product.ProductCategory;
import com.artur.orange_backend.model.utils.product.ProductCollection;
import com.artur.orange_backend.model.utils.product.ProductPrice;
import com.artur.orange_backend.model.utils.product.ProductSpecifications;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class ProductCreationDTO {
    @NotNull(message = "Product category is mandatory")
    private ProductCategory productCategory;

    @NotNull(message = "Product collection is mandatory")
    private ProductCollection productCollection;

    @NotBlank(message = "Product title is mandatory")
    private String title;

    @Embedded
    private ProductPrice price;

    private List<String> kit = new ArrayList<>();

    @NotBlank(message = "Description is mandatory")
    private String description;

    private List<Blob> productImages;

    private byte[] assemblyDiagram;

    private Map<String, String> advantages = new HashMap<>();

    private List<ProductSpecifications> productSpecifications;
}
