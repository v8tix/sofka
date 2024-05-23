package com.exercise.cuenta.mapper;


import com.exercise.cuenta.dto.AccountRequest;
import com.exercise.cuenta.dto.AccountResponse;
import com.exercise.cuenta.model.Account;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;
@Component
public class AccountMapper {
    private final ModelMapper modelMapper;

    public AccountMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;

        modelMapper.addMappings(new PropertyMap<AccountRequest, Account>() {
            @Override
            protected void configure() {
                skip(destination.getCuentaId());
            }
        });
    }

    public Account convertToAccount(AccountRequest accountRequest) {
        return modelMapper.map(accountRequest, Account.class);
    }

    public AccountResponse convertToAccountResponse(Account account) {
        return modelMapper.map(account, AccountResponse.class);
    }

    public void updateAccountFromRequest(AccountRequest accountRequest, Account account) {
        modelMapper.map(accountRequest, account);
    }
}
