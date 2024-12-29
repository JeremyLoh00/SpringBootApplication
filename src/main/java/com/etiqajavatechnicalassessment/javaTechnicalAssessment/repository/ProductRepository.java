package com.etiqajavatechnicalassessment.javaTechnicalAssessment.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.etiqajavatechnicalassessment.javaTechnicalAssessment.model.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
 // JpaRepository provides all the CRUD methods like findAll(), save(), deleteById(), etc.
}
