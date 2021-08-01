package com.moulam.springbootrestapi1.Services;

import com.moulam.springbootrestapi1.Exceptions.EtBadRequestException;
import com.moulam.springbootrestapi1.Exceptions.EtResourceNotFoundException;
import com.moulam.springbootrestapi1.Model.Transaction;

import java.util.List;

public interface TransactionService {

    List<Transaction> findAllTransactions(Integer userId, Integer categoryId);
    Transaction findTransactionById(Integer userId, Integer categoryId, Integer transactionId) throws EtResourceNotFoundException;
    Transaction addTransaction(Integer userId, Integer categoryId, Double amount, String note, Long transactionDate) throws EtBadRequestException;
    void updateTransaction(Integer userId, Integer categoryId, Integer transactionId, Transaction transaction) throws EtBadRequestException;
    void removeTransaction(Integer userId, Integer categoryId, Integer transactionId) throws EtResourceNotFoundException;

}
