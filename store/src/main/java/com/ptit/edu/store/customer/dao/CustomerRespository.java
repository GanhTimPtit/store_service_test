package com.ptit.edu.store.customer.dao;

import com.ptit.edu.store.customer.models.data.Customer;
import com.ptit.edu.store.customer.models.view.HeaderProfile;
import com.ptit.edu.store.customer.models.view.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CustomerRespository extends JpaRepository<Customer,String> {
    Customer findByUser_Id(String userID);
    @Query("select new com.ptit.edu.store.customer.models.view.HeaderProfile(" +
            "c.lastName," +
            "c.avatarUrl" +
            ") from Customer c where c.id = ?1")
    HeaderProfile getHeaderProfile(String id);

    @Query("select new com.ptit.edu.store.customer.models.view.Profile(c)" +
            "from Customer  c where c.id = ?1")
    Profile getProfile(String customerID);

    @Transactional
    @Modifying
    @Query("update Customer c set c.description = ?2 where c.id = ?1")
    void updateDescription(String customerID, String description);
}
