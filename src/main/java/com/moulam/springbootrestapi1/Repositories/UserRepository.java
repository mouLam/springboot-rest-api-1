package com.moulam.springbootrestapi1.Repositories;

import com.moulam.springbootrestapi1.Exceptions.EtAuthException;
import com.moulam.springbootrestapi1.Model.User;

public interface UserRepository{

    Integer create(String firstName, String lastName, String email, String password) throws EtAuthException;

    User findEmailAndPassword(String email, String password) throws EtAuthException;

    Integer getCountByEmail(String email);

    User findById(Integer userId);
}
