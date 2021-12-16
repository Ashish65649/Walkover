package com.walkover.api.service;

import com.walkover.api.entity.User;
import com.walkover.api.exception.UserAuthException;
import com.walkover.api.repository.UserRepo;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo repo ;

    @Override
    public Map<String, String> signUpUser(User user) throws UserAuthException {
        user.setUserId(java.util.UUID.randomUUID().toString());
        Optional<User> optional = this.repo.findById(user.getEmailId());
        if (optional.isPresent()) {
            throw new UserAuthException("User with email id already exists");
        }
        else {
            String hashPassword = BCrypt.hashpw(user.getPassword(),BCrypt.gensalt(10));
            user.setPassword(hashPassword);
            this.repo.save(user);
        }
        Map<String,String> map = new HashMap<>();
        map.put("success", "User registered successfully, please login");
        return map;
    }

    @Override
    public Map<String,String> validateUser(String email,String password) throws UserAuthException {

        Optional<User> optional = this.repo.findById(email);
        if(optional.isEmpty()) {
            throw new UserAuthException("No user exists with given email id");
        }
        else {
            if (BCrypt.checkpw(password, optional.get().getPassword()) != false) {
                Map<String,String> map = new HashMap<>();
                map.put("success","Logged In successfully");
                return map;
            } else {
                throw new UserAuthException("Invalid email or password");
            }
        }
    }

    @Override
    public Map<String, String> updatePassword(String email, String oldPass, String newPassword) throws UserAuthException {
        Optional<User> optional = this.repo.findById(email);
        if(optional.isEmpty()) {
            throw new UserAuthException("No user exists with given email id");
        }
        else {
            if(BCrypt.checkpw(oldPass,optional.get().getPassword()) == false) {
                throw new UserAuthException("Invalid email or password");
            }
            else {
                String hashPassword = BCrypt.hashpw(newPassword,BCrypt.gensalt(10));
                this.repo.save(new User(optional.get().getUserId(), email,hashPassword));
                Map<String,String> map = new HashMap<>();
                map.put("success" , "Password updated successfully");
                return map;
            }
        }
    }

    @Override
    public Map<String, String> deleteUser(String email, String password) throws UserAuthException {
        Optional<User> optional = this.repo.findById(email);
        if(optional.isEmpty()) {
            throw new UserAuthException("No user exists with given email id");
        }
        else {
            if (BCrypt.checkpw(password, optional.get().getPassword()) != false) {
                this.repo.deleteById(email);
                Map<String,String> map = new HashMap<>();
                map.put("success" , "User deleted successfully");
                return map;
            } else {
                throw new UserAuthException("Invalid email or password");
            }
        }
    }

    @Override
    public List<Map<String,String>> getAllUsers(int pageNo, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNo,pageSize);
        Page<User> page = this.repo.findAll(pageRequest);
        List<Map<String,String>> list = new ArrayList<>();
        for (User obj : page) {
            Map<String, String> map = new HashMap<>();
            map.put("user-id", obj.getUserId());
            map.put("email", obj.getEmailId());
            list.add(map);
        }
        return list;
    }
}
