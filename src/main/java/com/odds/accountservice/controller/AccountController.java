package com.odds.accountservice.controller;

import com.odds.accountservice.model.Account;
import com.odds.accountservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountRepository repository;

    @GetMapping("/all")
    public List<Account> getAllAccounts() {
        return repository.findAll();
    }
}
