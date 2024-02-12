package com.example.ecommerce.service;

import com.example.ecommerce.entity.Product;

public interface ProductService {
    Product createProduct(Product product);
    Product readProduct(String productId);
    Product updateProduct(String productId, Product updatedProduct);
    Product deleteProduct(String productId);
    Product applyDiscountOrTax(String productId, String type, double value);
}

