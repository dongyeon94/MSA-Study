package com.coffee.msa.domain.repository;

import com.coffee.msa.domain.MenuStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MenuStatusRepository extends JpaRepository<MenuStatus, Long> {
    Optional<MenuStatus> findByOrderId(String orderID);
}
