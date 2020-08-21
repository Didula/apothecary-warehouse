package com.apothecary.apothecarybackend.controllers;

import com.apothecary.apothecarybackend.exception.StoreControllerException;
import com.apothecary.apothecarybackend.exception.StoreControllerNotFoundException;
import com.apothecary.apothecarybackend.exception.StoreControllerValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLException;

@ControllerAdvice
@Slf4j
public class StoreControllerErrorAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({StoreControllerNotFoundException.class})
    public void handle(StoreControllerNotFoundException e) {
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({StoreControllerException.class, SQLException.class, NullPointerException.class})
    public void handle() {
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({StoreControllerValidationException.class})
    public void handle(StoreControllerValidationException e) {
    }
}