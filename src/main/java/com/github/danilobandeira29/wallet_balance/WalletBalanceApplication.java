package com.github.danilobandeira29.wallet_balance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.github.danilobandeira29.wallet_balance.repositories")
public class WalletBalanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WalletBalanceApplication.class, args);
	}

}
