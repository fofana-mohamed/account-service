package com.odds.accountservice.service;

import com.odds.accountservice.model.Account;
import com.odds.accountservice.repository.AccountRepository;
import org.openapitools.client.model.AccountItem;
import org.openapitools.client.model.AccountsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountsService {

    @Autowired
    private AccountRepository repository;

    public AccountsResponse returnAccounts() {

        List<Account> accounts = repository.findAll();

        if (accounts.isEmpty())
            return null;

        AccountsResponse accountsResponse = new AccountsResponse(accounts.stream().map(item -> new AccountItem(
                item.getFirstName(),
                item.getLastName(),
                item.getEmail(),
                item.getPhoneNumber(),
                item.getBankAccountNumber(),
                item.getId().toString()
        )).collect(Collectors.toList()));

        return accountsResponse;
    }

    public AccountsResponse returnAccount(Long id) {

        Optional<Account> accounts = repository.findById(id);

        if (accounts.isEmpty())
            return null;

        AccountsResponse accountsResponse = new AccountsResponse(accounts.stream().map(item -> new AccountItem(
                item.getFirstName(),
                item.getLastName(),
                item.getEmail(),
                item.getPhoneNumber(),
                item.getBankAccountNumber(),
                item.getId().toString()
        )).collect(Collectors.toList()));

        return accountsResponse;
    }
}
