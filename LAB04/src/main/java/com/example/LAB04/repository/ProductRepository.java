
package com.example.LAB04.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.LAB04.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}