package com.artur.orange_backend.controller;

import com.artur.orange_backend.dto.ProductCreationDTO;
import com.artur.orange_backend.dto.ProductViewDTO;
import com.artur.orange_backend.dto.mappers.ProductMapper;
import com.artur.orange_backend.model.Product;
import com.artur.orange_backend.service.ProductService;
import jakarta.validation.Valid;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/products")
public class ProductController {
    private final ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductViewDTO>> getAllProducts(){
        List<Product> products = productService.getAllProducts();
        List<ProductViewDTO> productViewDTOs = products.stream().map(productMapper::productToProductViewDTO).toList();
        return new ResponseEntity<>(productViewDTOs, HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductViewDTO> getProductById(@PathVariable("productId") Long productId){
        Product product = productService.getProductById(productId);
        ProductViewDTO productViewDTO = productMapper.productToProductViewDTO(product);
        return new ResponseEntity<>(productViewDTO, HttpStatus.OK);
    }

    @PostMapping
    public void addProduct(@Valid @RequestBody ProductCreationDTO productCreationDTO){
        Product product = productMapper.productCreationDTOtoProduct(productCreationDTO);
        productService.addProduct(product);
    }

    @PutMapping("/{productId}")
    public void updateProductById(@PathVariable("productId") Long productId,
                                  @Valid @RequestBody ProductCreationDTO productCreationDTO){
        Product product = productMapper.productCreationDTOtoProduct(productCreationDTO);
        productService.updateProduct(productId, product);
    }

    @DeleteMapping("/{productId}")
    public void deleteProductById(@PathVariable("productId") Long productId){
        productService.deleteProductById(productId);
    }
}
