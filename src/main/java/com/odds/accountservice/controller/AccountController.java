package com.odds.accountservice.controller;

import com.odds.accountservice.service.AccountsService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.openapitools.client.model.AccountsResponse;
import org.openapitools.client.model.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/account")
public class AccountController {

    @Autowired
    AccountsService accountsService;

    @GetMapping("/all")
    @ResponseBody
    @ApiOperation(value = "Get Accounts", notes = "Get Account Info")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Returns success with response body"),
            @ApiResponse(code = 400, message = "Request is invalid: Either required data is missing or data is not consistent", response = Error.class),
            @ApiResponse(code = 401, message = "Authorization Failure. Customer token does not match", response = Error.class),
            @ApiResponse(code = 403, message = "Access Forbidden", response = Error.class),
            @ApiResponse(code = 404, message = "Not Found", response = Error.class),
            @ApiResponse(code = 412, message = "Precondition validation failed", response = Error.class),
            @ApiResponse(code = 500, message = "Internal server error", response = Error.class),
            @ApiResponse(code = 502, message = "Bad Request from the external system", response = Error.class),
            @ApiResponse(code = 503, message = "Internal server error - System down for maintenance", response = Error.class),
            @ApiResponse(code = 504, message = "External system down or not reachable", response = Error.class)
    })
    public ResponseEntity<AccountsResponse> getAllAccounts() {

        AccountsResponse response = new AccountsResponse();

        try {
            response = accountsService.returnAccounts();
        } catch(Exception e) {
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "Get Account", notes = "Get a Specific Account's Info")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Returns success with response body"),
            @ApiResponse(code = 400, message = "Request is invalid: Either required data is missing or data is not consistent", response = Error.class),
            @ApiResponse(code = 401, message = "Authorization Failure. Customer token does not match", response = Error.class),
            @ApiResponse(code = 403, message = "Access Forbidden", response = Error.class),
            @ApiResponse(code = 404, message = "Not Found", response = Error.class),
            @ApiResponse(code = 412, message = "Precondition validation failed", response = Error.class),
            @ApiResponse(code = 500, message = "Internal server error", response = Error.class),
            @ApiResponse(code = 502, message = "Bad Request from the external system", response = Error.class),
            @ApiResponse(code = 503, message = "Internal server error - System down for maintenance", response = Error.class),
            @ApiResponse(code = 504, message = "External system down or not reachable", response = Error.class)
    })
    public ResponseEntity<?> getAccountById(@PathVariable Long id) {

        AccountsResponse response = new AccountsResponse();

        try {
            response = accountsService.returnAccount(id);

            if (response == null) {
                Error error = new Error("404", "Account was not found", "APPLICATION");
                return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
            }

        } catch(Exception e) {
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

//    @PostMapping("/new")
//    public Account addAccount(@RequestBody Account account) {
//        return repository.save(account);
//    }
//
//    @PutMapping("/update/{id}")
//    public Account updateAccount(@RequestBody Account updatedAccount, @PathVariable Long id) {
//        Account account = repository.findById(id).orElse(null);
//
//        if (account == null)
//            return null;
//
//        account.setBankAccountNumber(updatedAccount.getBankAccountNumber());
//        account.setEmail(updatedAccount.getEmail());
//        account.setFirstName(updatedAccount.getFirstName());
//        account.setLastName(updatedAccount.getLastName());
//        account.setPhoneNumber(updatedAccount.getPhoneNumber());
//
//        return repository.save(account);
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public void deleteAccount(@PathVariable Long id) {
//        repository.deleteById(id);
//    }
}
