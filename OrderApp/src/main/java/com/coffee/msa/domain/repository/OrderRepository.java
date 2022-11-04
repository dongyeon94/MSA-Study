package com.coffee.msa.domain.repository;

import com.coffee.msa.domain.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    Optional<OrderEntity> findByUuid(UUID uuid);
}
