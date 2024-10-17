package com.github.danilobandeira29.wallet_balance.balances;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/balances")
public class BalancesController {

    @Autowired BalancesService balancesService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable String id) {
        Account account = balancesService.getOne(id);
        if (account == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(account);
    }
}
