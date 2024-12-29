package com.etiqajavatechnicalassessment.javaTechnicalAssessment.controller;
import com.etiqajavatechnicalassessment.javaTechnicalAssessment.Exception.ResourceNotFoundException;
import com.etiqajavatechnicalassessment.javaTechnicalAssessment.controller.ProductController;
import com.etiqajavatechnicalassessment.javaTechnicalAssessment.model.Product;
import com.etiqajavatechnicalassessment.javaTechnicalAssessment.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@RestController
@RequestMapping("/api/products") 
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    //Get product list
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
    	logger.info("Getting product list");
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    //Get product by ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
    	logger.info("Getting product {}", id);
        Optional<Product> product = productService.getProductById(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    //Add new product
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
    	logger.info("Adding new product");
        Product savedProduct = productService.createProduct(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }
    //Update product by ID
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable String id, @RequestBody Product product) {
    	logger.info("Updating product {}", id);
    	Product updatedProduct = productService.updateProduct(id, product);
        if (updatedProduct != null) {
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } else {
            logger.error("Product with ID {} not found for update", id);
            throw new ResourceNotFoundException("Product not found for update");
        }
    }
    //Delete product by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
    	logger.info("Deleting product {}", id);
    	try {
            productService.deleteProduct(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException ex) {
            logger.error("Product {} not found", id);
            throw ex;
        } catch (Exception ex) {
            logger.error("Error deleting product {}", id, ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
