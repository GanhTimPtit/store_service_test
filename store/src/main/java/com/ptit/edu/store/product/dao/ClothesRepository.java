package com.ptit.edu.store.product.dao;

import com.ptit.edu.store.product.models.data.Clothes;
import com.ptit.edu.store.product.models.view.ClothesPreview;
import com.ptit.edu.store.product.models.view.ClothesViewModel;
import com.ptit.edu.store.product.models.view.RateClothesViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ClothesRepository extends JpaRepository<Clothes, String>{
    Clothes findById(String clothesID);

    @Query("select new com.ptit.edu.store.product.models.view.ClothesPreview(c) " +
            " from Clothes c ")
    Page<ClothesPreview> getAllClothesPreviews(Pageable pageable);

    @Query("select new com.ptit.edu.store.product.models.view.ClothesPreview(c) from Clothes c where c.category.id = ?1")
    Page<ClothesPreview> getSimilarClothesPreviews(Pageable pageable, String categoryID);

    @Query("select new com.ptit.edu.store.product.models.view.ClothesViewModel(c) from Clothes c where c.id = ?1")
    ClothesViewModel getClothesViewModel(String clothesID);


}
