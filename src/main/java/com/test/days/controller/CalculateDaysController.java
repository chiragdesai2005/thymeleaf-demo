package com.test.days.controller;

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

    /**
     * This method is responsible for user to redirect to home page
     *
     * @param model
     * @return - index.html
     */
    @GetMapping("/")
    public String get(Model model) {
        model.addAttribute("dates", Dates.builder().build());
        return "index";
    }

    /**
     * This method is responsible for user to calculate days for the given days
     *
     * @param model
     * @return - index.html
     */
    @PostMapping("/")
    public String calculate(@Valid Dates dates, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "index";
        }
        if (dates.getStartDate().isAfter(dates.getEndDate())) {
            result.rejectValue("startDate", "error.dates", "Start date must be after end date");
            return "index";
        }
        dates.setDaysBetween(DAYS.between(dates.getStartDate(), dates.getEndDate()));
        model.addAttribute("dates", dates);
        return "index";
    }
}
