package com.example.ecommerce.repository;

import org.springframework.stereotype.Repository;

import com.example.ecommerce.entity.Product;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class ProductRepository {

    private final Map<String, Product> productMap = new HashMap<>();

    public Product saveProduct(Product product) {
        String productId = UUID.randomUUID().toString();
        product.setProductId(productId);
        productMap.put(productId, product);
        return product;
    }

    public Product findProductById(String productId) {
        return productMap.get(productId);
    }

    public Product updateProduct(String productId, Product updatedProduct) {
        Product existingProduct = productMap.get(productId);
        if (existingProduct != null) {
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setDescription(updatedProduct.getDescription());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setQuantityAvailable(updatedProduct.getQuantityAvailable());
        }
        return existingProduct;
    }

    public Product deleteProduct(String productId) {
        return productMap.remove(productId);
    }
}
