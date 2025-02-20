package main.java.com.sitblueprint.admin.service;

import java.time.LocalDate;

public class SemesterPeriod {
    private LocalDate startDate;
    private LocalDate endDate;

    public SemesterPeriod(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}
