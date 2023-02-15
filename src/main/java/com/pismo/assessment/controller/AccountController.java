package com.pismo.assessment.controller;

import com.pismo.assessment.entity.Account;
import com.pismo.assessment.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
@Api(value = "Accounts API", tags = "Accounts API")
public class AccountController {

    private final AccountService accountService;

    @ApiOperation("Add Account")
    @PostMapping
    public ResponseEntity<Account> save(@RequestBody Account account) {
        return ResponseEntity.status(201).body(accountService.save(account));
    }

    @ApiOperation("Get account by Id")
    @GetMapping("/{id}")
    public ResponseEntity<Account> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(accountService.getById(id));
    }

}
