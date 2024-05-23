package com.exercise.cuenta.service;

import com.exercise.cuenta.model.Account;
import com.exercise.cuenta.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;


    @Override
    @Transactional
    public Account save(Account account) {
        log.info("Saving account: {}", account);
        return accountRepository.save(account);
    }
    @Override
    @Transactional
    public void delete(UUID id) {
        log.info("Deleting account with ID: {}", id);
        accountRepository.deleteById(id);
    }
    @Override
    @Transactional(readOnly = true)
    public List<Account> getAll() {
        log.info("Fetching all accounts");
        return accountRepository.findAll();
    }
    @Override
    @Transactional(readOnly = true)
    public Optional<Account> getById(UUID id) {
        log.info("Fetching account with ID: {}", id);
        return accountRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Account> getByClientId(UUID clientId) {
        log.info("Fetching accounts for client ID: {}", clientId);
        return accountRepository.findByClienteId(clientId);
    }
}