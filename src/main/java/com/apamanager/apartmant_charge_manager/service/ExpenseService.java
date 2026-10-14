package com.apamanager.apartmant_charge_manager.service;

import com.apamanager.apartmant_charge_manager.dto.request.RequestExpenseDto;
import com.apamanager.apartmant_charge_manager.dto.response.ResponseExpenseDto;

import java.util.List;

public interface ExpenseService {

    public ResponseExpenseDto findExpenseById(Integer id);
    // public ResponseExpenseDto findExpenseByTitle(String title);

    public List<ResponseExpenseDto> findAll();

    public ResponseExpenseDto addExpense(RequestExpenseDto requestExpenseDto);

//    public ResponseExpenseDto updateExpense(String title, RequestExpenseDto requestExpenseDto);

    public void deleteExpense(String title);

}
