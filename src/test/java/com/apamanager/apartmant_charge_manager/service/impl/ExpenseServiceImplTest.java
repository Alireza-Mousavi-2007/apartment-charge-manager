package com.apamanager.apartmant_charge_manager.service.impl;

import com.apamanager.apartmant_charge_manager.dto.request.RequestExpenseDto;
import com.apamanager.apartmant_charge_manager.dto.response.ResponseExpenseDto;
import com.apamanager.apartmant_charge_manager.entity.*;
import com.apamanager.apartmant_charge_manager.repository.ExpenseRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ExpenseServiceImplTest {

    @Mock
    private ExpenseRepository expenseRepository;
    @InjectMocks
    private ExpenseServiceImpl expenseService;

    private Authority authority;
    private Role role;
    private User user;
    private Building building;
    private Unit unit;
    private Expense expense;
    private ResponseExpenseDto responseExpenseDto;
    private RequestExpenseDto requestExpenseDto;

    @BeforeEach
    public void init() {
        authority = Authority.builder().authority("authority").build();
        role = Role.builder().role("role").authorities(Set.of(authority)).build();
        building = Building.builder().name("name").address("address").build();
        unit = Unit.builder().number("number").householdSize(1).building(building).user(user).debt(BigDecimal.valueOf(1)).build();
        user = User.builder().id(1).username("username").password("password").roles(Set.of(role)).building(building).unit(unit).build();
        expense = Expense.builder().id(1).title("title").amount(BigDecimal.valueOf(1)).building(building).dateTime(LocalDateTime.now()).build();
        responseExpenseDto = ResponseExpenseDto.builder().title("title").amount(BigDecimal.valueOf(1)).building(building).dateTime(LocalDateTime.now()).build();
        requestExpenseDto = RequestExpenseDto.builder().title("title").amount(BigDecimal.valueOf(1)).building(building).dateTime(LocalDateTime.now()).build();
    }


    @Test
    public void findExpenseById_found_responseExpenseDto(){
        when(expenseRepository.findById(anyInt())).thenReturn(Optional.of(expense));

        var test = expenseService.findExpenseById(expense.getId());

        Assertions.assertThat(test).isNotNull();
        Assertions.assertThat(test.getTitle()).isEqualTo(expense.getTitle());
        Assertions.assertThat(test.getAmount()).isEqualTo(expense.getAmount());
        Assertions.assertThat(test.getBuilding()).isEqualTo(expense.getBuilding());
        Assertions.assertThat(test.getDateTime()).isEqualTo(expense.getDateTime());
    }

    @Test
    public void findAll_found_listOf_responseExpenseDto(){
        when(expenseRepository.findAll()).thenReturn(List.of(expense));

        var test = expenseService.findAll();

        Assertions.assertThat(test).isNotEmpty();
        Assertions.assertThat(test.size()).isGreaterThan(0);
    }

    @Test
    public void addExpense_added_responseExpenseDto(){
        var test = expenseService.addExpense(requestExpenseDto);

        Assertions.assertThat(test).isNotNull();
        Assertions.assertThat(test.getTitle()).isEqualTo(expense.getTitle());
        Assertions.assertThat(test.getAmount()).isEqualTo(expense.getAmount());
        Assertions.assertThat(test.getBuilding()).isEqualTo(expense.getBuilding());

    }

    @Test
    public void deleteExpense_deleted_void(){
        when(expenseRepository.findById(anyInt())).thenReturn(Optional.of(expense));

        expenseService.deleteExpense(expense.getId());

        verify(expenseRepository , times(1)).delete(any());

    }

}
