package com.apamanager.apartmant_charge_manager.service.impl;

import com.apamanager.apartmant_charge_manager.dto.request.RequestExpenseDto;
import com.apamanager.apartmant_charge_manager.dto.response.ResponseExpenseDto;
import com.apamanager.apartmant_charge_manager.entity.Expense;
import com.apamanager.apartmant_charge_manager.exceptions.ExpenseNotFoundException;
import com.apamanager.apartmant_charge_manager.repository.ExpenseRepository;
import com.apamanager.apartmant_charge_manager.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private final ExpenseRepository expenseRepository;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public ResponseExpenseDto findExpenseById(Integer id) {
        var expense = expenseRepository.findById(id);

        if (expense.isEmpty()) throw new ExpenseNotFoundException("expense " + id + " doesn't exists");

        var expenseDto = ResponseExpenseDto.builder()
                .title(expense.get().getTitle())
                .amount(expense.get().getAmount())
                .building(expense.get().getBuilding())
                .dateTime(expense.get().getDateTime())
                .build();

        return expenseDto;
    }

    @Override
    public List<ResponseExpenseDto> findAll() {

        var expenses = expenseRepository.findAll();

        var expensesDto = expenses.stream()
                .map(a -> ResponseExpenseDto.builder()
                        .title(a.getTitle())
                        .amount(a.getAmount())
                        .dateTime(a.getDateTime())
                        .building(a.getBuilding())
                        .build())
                .toList();

        return expensesDto;
    }

    @Override
    public ResponseExpenseDto addExpense(RequestExpenseDto requestExpenseDto) {

        var expense = Expense.builder()
                .title(requestExpenseDto.getTitle())
                .amount(requestExpenseDto.getAmount())
                .building(requestExpenseDto.getBuilding())
                .dateTime(requestExpenseDto.getDateTime())
                .build();

        expenseRepository.save(expense);

        var expenseDto = ResponseExpenseDto.builder()
                .title(expense.getTitle())
                .amount(expense.getAmount())
                .building(expense.getBuilding())
                .dateTime(expense.getDateTime())
                .build();

        return expenseDto;
    }

    @Override
    public void deleteExpense(Integer id) {

        var expense = expenseRepository.findById(id);
        if (expense.isEmpty()) throw new ExpenseNotFoundException("expense " + id + " doesn't exists");

        expenseRepository.delete(expense.get());

    }
}
