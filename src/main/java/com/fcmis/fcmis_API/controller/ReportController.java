package com.fcmis.fcmis_API.controller;

import com.fcmis.fcmis_API.DTOs.SummaryResponse;
import com.fcmis.fcmis_API.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController @RequestMapping("/api/reports") @RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    @GetMapping("/summary")
    public SummaryResponse getSummary(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to
    ) {
        return reportService.summary(from, to);
    }

    @GetMapping("/dashboard")
    public SummaryResponse dashboard() {
        LocalDate today = LocalDate.now();
        LocalDate start = today.withDayOfMonth(1);
        return reportService.summary(start, today);
    }
}