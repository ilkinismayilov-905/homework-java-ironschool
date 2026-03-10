package org.example.springboothomework;

import org.example.springboothomework.controller.SchoolController;
import org.example.springboothomework.service.SchoolService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class SchoolControllerTest {

    private MockMvc mockMvc;

    @Mock
    private SchoolService schoolService;

    @InjectMocks
    private SchoolController schoolController;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(schoolController).build();
    }

    @Test
    void shouldReturnProfit() throws Exception {
        when(schoolService.showProfit()).thenReturn(2000.0);

        mockMvc.perform(get("/api/school/profit"))
                .andExpect(status().isOk())
                .andExpect(content().string("2000.0"));
    }

    @Test
    void shouldReturnZeroProfitWhenNoData() throws Exception {
        when(schoolService.showProfit()).thenReturn(0.0);

        mockMvc.perform(get("/api/school/profit"))
                .andExpect(status().isOk())
                .andExpect(content().string("0.0"));
    }

    @Test
    void shouldReturnNegativeProfitWhenLoss() throws Exception {
        when(schoolService.showProfit()).thenReturn(-500.0);

        mockMvc.perform(get("/api/school/profit"))
                .andExpect(status().isOk())
                .andExpect(content().string("-500.0"));
    }

    @Test
    void shouldReturnMoneyEarned() throws Exception {
        when(schoolService.showMoneyEarned()).thenReturn(5000.0);

        mockMvc.perform(get("/api/school/moneyEarn"))
                .andExpect(status().isOk())
                .andExpect(content().string("5000.0"));
    }

    @Test
    void shouldReturnZeroMoneyEarnedWhenNoEnrollments() throws Exception {
        when(schoolService.showMoneyEarned()).thenReturn(0.0);

        mockMvc.perform(get("/api/school/moneyEarn"))
                .andExpect(status().isOk())
                .andExpect(content().string("0.0"));
    }

    @Test
    void shouldReturnMoneySpent() throws Exception {
        when(schoolService.showMoneySpent()).thenReturn(3000.0);

        mockMvc.perform(get("/api/school/moneySpent"))
                .andExpect(status().isOk())
                .andExpect(content().string("3000.0"));
    }

    @Test
    void shouldReturnZeroMoneySpentWhenNoTeachers() throws Exception {
        when(schoolService.showMoneySpent()).thenReturn(0.0);

        mockMvc.perform(get("/api/school/moneySpent"))
                .andExpect(status().isOk())
                .andExpect(content().string("0.0"));
    }
}