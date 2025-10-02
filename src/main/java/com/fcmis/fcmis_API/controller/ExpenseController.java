package com.fcmis.fcmis_API.controller;

import com.fcmis.fcmis_API.DTOs.CreateExpenseRequest;
import com.fcmis.fcmis_API.domain.Expense;
import com.fcmis.fcmis_API.repo.ExpenseRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/api/expenses") @RequiredArgsConstructor
public class ExpenseController {
    private final ExpenseRepository expenseRepo;

    @PostMapping
    public Expense create(@Valid @RequestBody CreateExpenseRequest req) {
        Expense e = Expense.builder()
                .type(req.getType())
                .date(req.getDate())
                .amount(req.getAmount())
                .note(req.getNote())
                .build();
        return expenseRepo.save(e);
    }

    @GetMapping
    public List<Expense> getAllExpenses() { return expenseRepo.findAll(); }
}