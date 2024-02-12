package com.example.ecommerce;

import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.example.ecommerce.entity.Product;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.service.ProductServiceImpl;

public class ProductServiceTest {
	
	@Mock
	ProductRepository 	productRepository ;
	
	@InjectMocks
    ProductServiceImpl productServiceImpl;
	@BeforeEach
	public void setup() {
	MockitoAnnotations.openMocks(this);
	}
    
    @Test
	public void testcreateProduct(){
    	
    	Product product=new Product();
    	product.setName("Example Product");
    	product.setDescription("this is the testing product");
    	product.setPrice(50);
    	product.setQuantityAvailable(0);
    	
    	Product productresponse=new Product();
    	productresponse.setName("Example Product");
    	productresponse.setDescription("this is the testing product");
    	productresponse.setPrice(50);
    	productresponse.setQuantityAvailable(0);
    	String productId = UUID.randomUUID().toString();
    	productresponse.setProductId(productId);
    	Mockito.when(productRepository.saveProduct(product)).thenReturn(productresponse);
    	
    	Product result=productServiceImpl.createProduct(product);
    	Assertions.assertNotNull(result);
		Assertions.assertEquals(productId, result.getProductId());
		
		Assertions.assertEquals("Example Product", result.getName());
		
		
	}
    @Test
    public void testReadProduct() {
    	Product productresponse=new Product();
    	productresponse.setName("Example Product");
    	productresponse.setDescription("this is the testing product");
    	productresponse.setPrice(50);
    	productresponse.setQuantityAvailable(0);
    	productresponse.setProductId("61a95bd0-3bd7-465d-96dc-f294ec3426ba");
    	Mockito.when(productRepository.findProductById("61a95bd0-3bd7-465d-96dc-f294ec3426ba")).thenReturn(productresponse);
    	
    	Product result=productServiceImpl.readProduct("61a95bd0-3bd7-465d-96dc-f294ec3426ba");
    	Assertions.assertNotNull(result);
		Assertions.assertEquals("61a95bd0-3bd7-465d-96dc-f294ec3426ba", result.getProductId());
		
		Assertions.assertEquals("Example Product", result.getName());
    	
    }
    
    @Test
    public void testDiscountOrTax() {
    	Product product=new Product();
    	product.setName("Example Product");
    	product.setDescription("this is the testing product");
    	product.setPrice(50);
    	product.setQuantityAvailable(50);
    	product.setProductId("61a95bd0-3bd7-465d-96dc-f294ec3426ba");
    	Mockito.when(productRepository.findProductById("61a95bd0-3bd7-465d-96dc-f294ec3426ba")).thenReturn(product);
    	
    	Product productresponse=new Product();
    	productresponse.setName("Example Product");
    	productresponse.setDescription("this is the testing product");
    	productresponse.setPrice(45);
    	productresponse.setQuantityAvailable(50);
    	productresponse.setProductId("61a95bd0-3bd7-465d-96dc-f294ec3426ba");
    	
    	Mockito.when(productRepository.updateProduct("61a95bd0-3bd7-465d-96dc-f294ec3426ba",productresponse)).thenReturn(productresponse);
    	Product result=productServiceImpl.applyDiscountOrTax("61a95bd0-3bd7-465d-96dc-f294ec3426ba", "discount", 10);
    	
    	Assertions.assertNotNull(result);
		Assertions.assertEquals("61a95bd0-3bd7-465d-96dc-f294ec3426ba", result.getProductId());
		
		Assertions.assertEquals("Example Product", result.getName());
		Assertions.assertEquals(45, result.getPrice());
		
    	
    }

}
