package com.github.danilobandeira29.wallet_balance;

import com.github.danilobandeira29.wallet_balance.balances.Account;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class AccountTests {

    @Test
    public void testCreateAccount() {
        String accountId = "0502ee10-1536-435e-99c0-123c265e96c3";
        Integer balance = 10;
        Account account = new Account(accountId, balance);
        assertEquals(accountId, account.getId());
        assertEquals(balance, account.getBalance());
    }
}
