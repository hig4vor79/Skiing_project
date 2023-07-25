package com.skiing.repositories;

import com.skiing.models.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

    @Query("SELECT o FROM Order AS o WHERE o.client.id = :id")
    List<Order> getByClientId(@Param("id") String id);

    @Query("SELECT o FROM Order AS o WHERE o.manager IS NULL AND o.canceled = false OR o.manager.id = :id")
    List<Order> getForManager(@Param("id") String id);

    @Query("SELECT o FROM Order AS o WHERE o.manager IS NOT NULL AND o.canceled = false AND o.courier IS NULL OR o.endDateCourier IS NULL")
    List<Order> getForCourier();
}
