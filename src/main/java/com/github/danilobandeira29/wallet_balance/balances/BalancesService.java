package com.github.danilobandeira29.wallet_balance.balances;

import com.github.danilobandeira29.wallet_balance.repositories.AccountEntity;
import com.github.danilobandeira29.wallet_balance.repositories.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BalancesService {
    private final AccountsRepository accountsRepository;

    @Autowired
    public BalancesService(AccountsRepository accountsRepository) {
        this.accountsRepository = accountsRepository;
    }

    public Account getOne(String id) {
        Optional<AccountEntity> accountEntityOpt = accountsRepository.findById(id);
        AccountEntity accountEntity = accountEntityOpt.orElse(null);
        if (accountEntity == null) {
            return null;
        }
        return new Account(accountEntity.getId(), accountEntity.getBalance());
    }

    public void upsert(List<Account> accounts) {
        for (Account a : accounts) {
            AccountEntity accountEntity = new AccountEntity();
            accountEntity.setBalance(a.getBalance());
            accountEntity.setId(a.getId());
            accountsRepository.save(accountEntity);
        }
    }
}
