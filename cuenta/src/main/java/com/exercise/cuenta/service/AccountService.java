package com.exercise.cuenta.service;

import com.exercise.cuenta.model.Account;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
public interface AccountService {
    Account save(Account account);
    void delete(UUID id);
    List<Account> getByClientId(UUID clientId);
    List<Account> getAll();
    Optional<Account> getById(UUID id);
}