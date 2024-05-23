package com.exercise.cuenta.service;

import com.exercise.cuenta.dto.ReportResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
public interface ReportService {
    List<ReportResponse> generateReport(LocalDateTime startDate, LocalDateTime endDate, UUID clientId);
}