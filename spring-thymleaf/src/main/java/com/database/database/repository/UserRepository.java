package com.database.database.repository;

import com.database.database.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users,String> {
 public Users findByUsername(String username);
 public Users findByEmail(String email);
}
