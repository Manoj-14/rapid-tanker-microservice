package com.project.orderservice.repository;

import com.project.orderservice.modal.WaterOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<WaterOrder,String> {
}
