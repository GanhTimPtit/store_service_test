package com.ptit.edu.store.admin.dao;

import com.ptit.edu.store.admin.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,String>{
}
