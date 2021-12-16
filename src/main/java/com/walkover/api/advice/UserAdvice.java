package com.walkover.api.advice;

import com.walkover.api.exception.UserAuthException;
import com.walkover.api.message.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestControllerAdvice
public class UserAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> userDetailsWrongFormat(MethodArgumentNotValidException ex) {

        List<ObjectError> errors = ex.getBindingResult().getAllErrors();
        Message msg = new Message();
        msg.setDateTime(new Date());
        msg.setStatusCode(HttpStatus.BAD_REQUEST.value());
        List<String> list = new ArrayList<>();
        errors.stream().forEach(obj -> list.add(obj.getDefaultMessage()));
        msg.setMessages(list);
        return new ResponseEntity<Message>(msg,HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(UserAuthException.class)
    public ResponseEntity<?> userExists(UserAuthException ex) {
        Message msg = new Message();
        msg.setDateTime(new Date());
        msg.setStatusCode(HttpStatus.FORBIDDEN.value());
        List<String> list = new ArrayList<>();
        list.add(ex.getMessage());
        msg.setMessages(list);
        return new ResponseEntity<>(msg,HttpStatus.FORBIDDEN);
    }

}
