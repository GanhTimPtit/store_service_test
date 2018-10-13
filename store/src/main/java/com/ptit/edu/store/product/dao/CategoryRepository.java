package com.ptit.edu.store.product.dao;

import com.ptit.edu.store.product.models.data.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String> {
}
