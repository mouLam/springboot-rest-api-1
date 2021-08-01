package com.moulam.springbootrestapi1.Repositories;

import com.moulam.springbootrestapi1.Exceptions.EtBadRequestException;
import com.moulam.springbootrestapi1.Exceptions.EtResourceNotFoundException;
import com.moulam.springbootrestapi1.Model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository {

    private static final String SQL_CREATE = "INSERT INTO ET_TRANSACTIONS (TRANSACTION_ID, CATEGORY_ID, USER_ID, AMOUNT, NOTE, TRANSACTION_DATE)" +
            " VALUES(NEXTVAL('ET_TRANSACTIONS_SEQ'), ?, ?, ?, ?, ?)";

    private static final String SQL_FIND_BY_ID = "SELECT TRANSACTION_ID, CATEGORY_ID, USER_ID, AMOUNT, NOTE, TRANSACTION_DATE " +
            "FROM ET_TRANSACTIONS WHERE USER_ID = ? AND CATEGORY_ID = ? AND TRANSACTION_ID = ?";

    private static final String SQL_FIND_ALL = "SELECT TRANSACTION_ID, CATEGORY_ID, USER_ID, AMOUNT, NOTE, TRANSACTION_DATE " +
            "FROM ET_TRANSACTIONS WHERE USER_ID = ? AND CATEGORY_ID = ? ";

    private static final String SQL_UPDATE = "UPDATE ET_TRANSACTIONS SET AMOUNT = ?, NOTE = ?, TRANSACTION_DATE = ? " +
            "WHERE USER_ID = ? AND CATEGORY_ID = ? AND TRANSACTION_ID = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public TransactionRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Transaction> findAll(Integer userId, Integer categoryId) {
        return jdbcTemplate.query(SQL_FIND_ALL, new Object[]{userId, categoryId}, transactionRowMapper);
    }

    @Override
    public Transaction findById(Integer userId, Integer categoryId, Integer transactionId) throws EtResourceNotFoundException {
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{userId, categoryId, transactionId}, transactionRowMapper);
        }catch (Exception e){
            throw new EtResourceNotFoundException("Transaction not found");
        }
    }

    @Override
    public Integer create(Integer userId, Integer categoryId, Double amount, String note, Long transactionDate) throws EtBadRequestException {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setInt(1, categoryId);
                preparedStatement.setInt(2, userId);
                preparedStatement.setDouble(3, amount);
                preparedStatement.setString(4, note);
                preparedStatement.setLong(5, transactionDate);
                return preparedStatement;
            }, keyHolder);
            return (Integer) Objects.requireNonNull(keyHolder.getKeys()).get("TRANSACTION_ID");
        }catch (Exception e){
            throw new EtBadRequestException("Invalid creating transaction request");
        }
    }

    @Override
    public void update(Integer userId, Integer categoryId, Integer transactionId, Transaction transaction) throws EtBadRequestException {
        try {
            jdbcTemplate.update(SQL_UPDATE, new Object[]{transaction.getAmount(),
                    transaction.getNote(),
                    transaction.getTransactionDate(),
                    userId,
                    categoryId,
                    transactionId});
        }catch (Exception e){
            throw new EtBadRequestException("Invalid transaction update");
        }
    }

    @Override
    public void remove(Integer userId, Integer categoryId, Integer transactionId) throws EtResourceNotFoundException {

    }

    private final RowMapper<Transaction> transactionRowMapper = ((resultSet, i) -> new Transaction(resultSet.getInt("TRANSACTION_ID"),
            resultSet.getInt("CATEGORY_ID"),
            resultSet.getInt("USER_ID"),
            resultSet.getDouble("AMOUNT"),
            resultSet.getString("NOTE"),
            resultSet.getLong("TRANSACTION_DATE")));
}
