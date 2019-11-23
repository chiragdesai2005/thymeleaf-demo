package com.test.days.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;

@Builder
@Data
@AllArgsConstructor
public class Dates {
    @DateTimeFormat(iso = DATE, pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @DateTimeFormat(iso = DATE, pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    private long daysBetween;
}
