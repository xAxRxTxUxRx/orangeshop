package com.artur.orange_backend.dto.mappers;

import com.artur.orange_backend.dto.ProductCreationDTO;
import com.artur.orange_backend.dto.ProductViewDTO;
import com.artur.orange_backend.model.Product;
import org.mapstruct.Mapper;

@Mapper
public interface ProductMapper {
    Product productCreationDTOtoProduct(ProductCreationDTO dto);
    ProductViewDTO productToProductViewDTO(Product product);
}
