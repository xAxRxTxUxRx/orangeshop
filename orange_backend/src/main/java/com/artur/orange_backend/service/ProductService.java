package com.artur.orange_backend.service;

import com.artur.orange_backend.exception.EntityNotFoundByIdException;
import com.artur.orange_backend.model.Product;
import com.artur.orange_backend.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long productId) {
        Optional<Product> productOptional = productRepository.findById(productId);

        return productOptional.orElseThrow(() -> new EntityNotFoundByIdException("Product", productId));
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public void updateProduct(Long productId, Product product) {
        Product productToUpdate = getProductById(productId);
        productToUpdate.setProductCategory(product.getProductCategory());
        productToUpdate.setProductCollection(product.getProductCollection());
        productToUpdate.setTitle(product.getTitle());
        productToUpdate.setPrice(product.getPrice());
        productToUpdate.setKit(product.getKit());
        productToUpdate.setDescription(product.getDescription());
        productToUpdate.setProductImages(product.getProductImages());
        productToUpdate.setAssemblyDiagram(product.getAssemblyDiagram());
        productToUpdate.setAdvantages(product.getAdvantages());
        productToUpdate.setProductSpecifications(product.getProductSpecifications());

        productRepository.save(productToUpdate);
    }

    public void deleteProductById(Long productId) {
        if (!productRepository.existsById(productId)){
            throw new EntityNotFoundByIdException("Product", productId);
        }

        productRepository.deleteById(productId);
    }
}
