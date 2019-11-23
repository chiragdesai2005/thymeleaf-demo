package com.test.days.controller;

import com.test.days.model.Dates;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.time.LocalDate;

import static java.time.Month.NOVEMBER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class CalculateDaysControllerTest {
    private static CalculateDaysController calculateDaysController;
    private static BindingResult mockedBindingResult;
    private static Model mockedModel;


    @BeforeClass
    public static void setUpCalculateDaysControllerInstance() {
        mockedBindingResult = mock(BindingResult.class);
        mockedModel = mock(Model.class);
        calculateDaysController = new CalculateDaysController();
    }

    @Test
    public void whenCalledshowHomePage_thenIndex() {
        Dates dates = Dates.builder().build();
        assertThat(calculateDaysController.get(mockedModel)).isEqualTo("index");
    }

    @Test
    public void whenCalledConfirm_thenCorrect() {
        Dates dates = Dates.builder().startDate(LocalDate.of(2019, NOVEMBER, 11)).endDate(LocalDate.of(2019, NOVEMBER, 13)).build();
        when(mockedBindingResult.hasErrors()).thenReturn(false);
        assertThat(calculateDaysController.calculate(dates, mockedBindingResult, mockedModel)).isEqualTo("index");
    }

    @Test
    public void whenCalledConfirm_InvaidInput_thenShowError() {
        Dates dates = Dates.builder().startDate(LocalDate.of(2019, NOVEMBER, 19)).endDate(LocalDate.of(2019, NOVEMBER, 13)).build();
        when(mockedBindingResult.hasErrors()).thenReturn(true);
        assertThat(calculateDaysController.calculate(dates, mockedBindingResult, mockedModel)).isEqualTo("index");
    }

}
