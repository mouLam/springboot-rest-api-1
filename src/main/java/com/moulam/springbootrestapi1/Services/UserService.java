package com.moulam.springbootrestapi1.Services;

import com.moulam.springbootrestapi1.Exceptions.EtAuthException;
import com.moulam.springbootrestapi1.Model.User;

public interface UserService {

    User validateUser(String email, String password) throws EtAuthException;

    User registerUser(String firstName, String lastName, String email, String password) throws EtAuthException;


}
