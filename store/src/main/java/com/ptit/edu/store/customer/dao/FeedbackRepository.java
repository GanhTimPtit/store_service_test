package com.ptit.edu.store.customer.dao;

import com.ptit.edu.store.customer.models.data.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback,String> {
}
