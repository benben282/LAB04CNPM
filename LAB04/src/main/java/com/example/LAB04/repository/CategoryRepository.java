
package com.example.LAB04.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.LAB04.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}