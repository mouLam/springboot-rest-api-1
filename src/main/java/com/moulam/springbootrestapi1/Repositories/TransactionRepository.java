package com.moulam.springbootrestapi1.Repositories;

import com.moulam.springbootrestapi1.Exceptions.EtBadRequestException;
import com.moulam.springbootrestapi1.Exceptions.EtResourceNotFoundException;
import com.moulam.springbootrestapi1.Model.Transaction;

import java.util.List;

public interface TransactionRepository {

    List<Transaction> findAll(Integer userId, Integer categoryId);
    Transaction findById(Integer userId, Integer categoryId, Integer transactionId) throws EtResourceNotFoundException;
    Integer create(Integer userId, Integer categoryId, Double amount, String note, Long transactionDate) throws EtBadRequestException;
    void update(Integer userId, Integer categoryId, Integer transactionId, Transaction transaction) throws EtBadRequestException;
    void remove(Integer userId, Integer categoryId, Integer transactionId) throws EtResourceNotFoundException;
}
