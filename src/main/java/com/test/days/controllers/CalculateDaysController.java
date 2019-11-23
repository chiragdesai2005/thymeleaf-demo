package com.test.days.controllers;

import com.test.days.model.Dates;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

import static java.time.temporal.ChronoUnit.DAYS;


@Controller
public class CalculateDaysController {

    @GetMapping("/")
    public String get(Model model) {
        model.addAttribute("dates", Dates.builder().build());
        return "index";
    }

    @PostMapping("/")
    public String calculate(final @Valid Dates dates, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "index";
        }
        if(dates.getStartDate().isAfter(dates.getEndDate())){
            result.rejectValue("startDate", "error.dates","Start date must be after end date");
            return "index";
        }
        dates.setDaysBetween(DAYS.between(dates.getStartDate(), dates.getEndDate()));
        model.addAttribute("dates", dates);
        return "index";
    }
}
