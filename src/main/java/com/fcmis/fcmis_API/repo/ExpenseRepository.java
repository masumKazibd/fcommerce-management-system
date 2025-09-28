package com.fcmis.fcmis_API.repo;
import com.fcmis.fcmis_API.domain.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByDateBetween(LocalDate from, LocalDate to);
}