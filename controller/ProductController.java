package com.example.ecommerce.controller;

import com.example.ecommerce.entity.Product;
import com.example.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public Object createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @GetMapping("/{productId}")
    public Object readProduct(@PathVariable String productId) {
        Product product = productService.readProduct(productId);
        return product != null ? product : "Product not found";
    }

    @PutMapping("/{productId}")
    public Object updateProduct(@PathVariable String productId, @RequestBody Product updatedProduct) {
        return productService.updateProduct(productId, updatedProduct) != null ? "Update successful" : "Product not found";
    }

    @DeleteMapping("/{productId}")
    public Object deleteProduct(@PathVariable String productId) {
        return productService.deleteProduct(productId) != null ? "Delete successful" : "Product not found";
    }

    @PostMapping("/{productId}/apply-discount-or-tax")
    public Object applyDiscountOrTax(@PathVariable String productId, @RequestParam String type, @RequestParam double value) {
        Product result = productService.applyDiscountOrTax(productId, type, value);
        return result != null ? result : "Product not found or invalid operation";
    }
}

