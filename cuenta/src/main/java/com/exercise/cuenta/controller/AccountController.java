package com.exercise.cuenta.controller;

import com.exercise.cuenta.dto.AccountResponse;
import com.exercise.cuenta.dto.AccountRequest;

import com.exercise.cuenta.mapper.AccountMapper;
import com.exercise.cuenta.model.Account;
import com.exercise.cuenta.validation.Groups;
import com.exercise.cuenta.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cuentas")
public class AccountController {

    private final AccountService accountService;
    private final AccountMapper accountMapper;


    @PostMapping
    public ResponseEntity<AccountResponse> create(@Validated(Groups.OnCreate.class)
                                                  @RequestBody AccountRequest accountRequest) {
        Account account = accountMapper.convertToAccount(accountRequest);
        Account savedAccount = accountService.save(account);
        URI location = URI.create(String.format("/cuentas/%s", savedAccount.getCuentaId()));
        return ResponseEntity.created(location).body(accountMapper.convertToAccountResponse(savedAccount));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        Optional<Account> cuenta = accountService.getById(id);
        if (cuenta.isPresent()) {
            accountService.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping
    public ResponseEntity<List<AccountResponse>> getAll() {
        List<Account> accounts = accountService.getAll();
        List<AccountResponse> response = accounts.stream()
                .map(accountMapper::convertToAccountResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> getById(@PathVariable UUID id) {
        Optional<Account> cuenta = accountService.getById(id);
        return cuenta.map(account -> ResponseEntity.ok(accountMapper.convertToAccountResponse(account)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountResponse> completeUpdate(
            @PathVariable UUID id,
            @Validated(Groups.OnUpdate.class)
            @RequestBody  AccountRequest accountRequest
    ) {
        return updateAccount(id, accountRequest);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AccountResponse> update(
            @PathVariable UUID id,
            @RequestBody AccountRequest accountRequest
    ) {
        return updateAccount(id, accountRequest);
    }

    private ResponseEntity<AccountResponse> updateAccount(UUID id, AccountRequest accountRequest) {
        return accountService.getById(id)
                .map(account -> {
                    accountMapper.updateAccountFromRequest(accountRequest, account);
                    Account updatedAccount = accountService.save(account);
                    return ResponseEntity.ok(accountMapper.convertToAccountResponse(updatedAccount));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}