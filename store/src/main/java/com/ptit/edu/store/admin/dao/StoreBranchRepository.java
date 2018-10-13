package com.ptit.edu.store.admin.dao;

import com.ptit.edu.store.admin.models.StoreBranch;
import com.ptit.edu.store.admin.models.view.StoreBranchViewModel;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StoreBranchRepository extends JpaRepository<StoreBranch,String> {
    @Query("select new com.ptit.edu.store.admin.models.view.StoreBranchViewModel(sb) from StoreBranch sb")
    List<StoreBranchViewModel> getStoreBranchViewModel(Sort sort);

//    @Query("select " +
//            "6371 *2* ASIN(SQRT(POWER(SIN((lat1 - abs(lat2)) * pi()/180 / 2),2) + COS(lat1 * pi()/180 ) * COS(abs(lat2) *pi()/180) * POWER(SIN((lon1 - lon2) *pi()/180 / 2), 2) ))\n" +
//            "from StoreBranch sb")
//    List<Double> getDistance();
}
