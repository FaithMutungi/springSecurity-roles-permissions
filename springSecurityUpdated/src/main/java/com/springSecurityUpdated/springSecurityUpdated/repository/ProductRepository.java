package com.springSecurityUpdated.springSecurityUpdated.repository;

import com.springSecurityUpdated.springSecurityUpdated.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {


}
