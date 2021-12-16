package com.walkover.api.service;

import com.walkover.api.entity.User;
import com.walkover.api.exception.UserAuthException;

import java.util.List;
import java.util.Map;

public interface UserService {

    Map<String,String> signUpUser(User user) throws UserAuthException ;

    Map<String,String> validateUser(String email,String password) throws UserAuthException ;

    Map<String,String> updatePassword(String email,String oldPass,String newPassword) throws UserAuthException ;

    Map<String,String> deleteUser(String email,String password) throws UserAuthException ;

    List<Map<String,String>> getAllUsers(int pageNo,int pageSize);

}