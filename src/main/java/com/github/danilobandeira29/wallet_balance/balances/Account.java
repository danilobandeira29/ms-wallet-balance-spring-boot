package com.github.danilobandeira29.wallet_balance.balances;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Account {
    private String id;
    private Integer balance;
}
