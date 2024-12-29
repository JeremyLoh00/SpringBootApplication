package com.etiqajavatechnicalassessment.javaTechnicalAssessment.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.etiqajavatechnicalassessment.javaTechnicalAssessment.model.Product;
import com.etiqajavatechnicalassessment.javaTechnicalAssessment.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    //Get all product
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    //Get specific product by ID
    public Optional<Product> getProductById(String id) {
        return productRepository.findById(id);
    }
    //Add new product
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
    //Update product
    public Product updateProduct(String id, Product product) {
        if (productRepository.existsById(id)) {
            product.setId(id);
            return productRepository.save(product);
        }
        return null;
    }
    //Delete product by ID
    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }
}
