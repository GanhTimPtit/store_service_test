package com.ptit.edu.store.customer.dao;

import com.ptit.edu.store.customer.models.data.Rate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RateRepository extends JpaRepository<Rate,String> {
}
