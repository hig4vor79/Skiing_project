package com.skiing.repositories;

import com.skiing.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Query("SELECT u FROM User AS u WHERE u.phone = :phone")
    User getByPhone(@Param("phone") String phone);
}
