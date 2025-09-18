package com.fcmis.fcmis_API.repo;

import com.fcmis.fcmis_API.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    List<Sale> findByDateBetween(LocalDate from, LocalDate to);
}
