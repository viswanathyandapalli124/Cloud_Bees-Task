package com.example.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.entity.Product;
import com.example.ecommerce.repository.ProductRepository;

@Service
public class ProductServiceImpl  implements ProductService{
	@Autowired
    private ProductRepository productRepository;

    public Product createProduct(Product product) {
        return productRepository.saveProduct(product);
    }

    public Product readProduct(String productId) {
        return productRepository.findProductById(productId);
    }

    public Product updateProduct(String productId, Product updatedProduct) {
        return productRepository.updateProduct(productId, updatedProduct);
    }

    public Product deleteProduct(String productId) {
        return productRepository.deleteProduct(productId);
    }

    public Product applyDiscountOrTax(String productId, String type, double value) {
        Product product = productRepository.findProductById(productId);
        if (product == null) {
            // Product not found
            return null;
        }

        if ("discount".equalsIgnoreCase(type)) {
            // Apply discount
            double discountedPrice = product.getPrice() * (1 - value / 100);
            product.setPrice(discountedPrice);
        } else if ("tax".equalsIgnoreCase(type)) {
            // Apply tax
            double taxedPrice = product.getPrice() * (1 + value / 100);
            product.setPrice(taxedPrice);
        } else {
            // Invalid type
            return null;
        }

        // Update product details
        productRepository.updateProduct(productId, product);

        return product;
    }
}
