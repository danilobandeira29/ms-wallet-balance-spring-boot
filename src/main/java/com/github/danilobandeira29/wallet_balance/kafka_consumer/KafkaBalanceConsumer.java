package com.github.danilobandeira29.wallet_balance.kafka_consumer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.danilobandeira29.wallet_balance.balances.Account;
import com.github.danilobandeira29.wallet_balance.balances.BalancesService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
class BalanceDto {
    @JsonProperty("account_id_from")
    private String accountIdFrom;

    @JsonProperty("account_id_to")
    private String accountIdTo;

    @JsonProperty("balance_account_from")
    private Integer balanceAccountFrom;

    @JsonProperty("balance_account_to")
    private Integer balanceAccountTo;

    public BalanceDto() {}
}

@Getter
@Setter
@AllArgsConstructor
class BalanceRecord {
    @JsonProperty("Name")
    private String Name;
    @JsonProperty("Payload")
    private BalanceDto Payload;

    public BalanceRecord() {}
}

@Service
public class KafkaBalanceConsumer {
    private final BalancesService balancesService;
    private final ObjectMapper objectMapper;

    public KafkaBalanceConsumer(BalancesService bs) {
        balancesService = bs;
        objectMapper = new ObjectMapper();
    }

    @KafkaListener(topics = "balances", groupId = "wallet")
    private void consume(ConsumerRecord<String, String> record) {
        try {
            System.out.println("string received " + record.value());
            BalanceRecord balanceRecord = objectMapper.readValue(record.value(), BalanceRecord.class);
            BalanceDto balanceDto = balanceRecord.getPayload();
            Account to = new Account(balanceDto.getAccountIdTo(), balanceDto.getBalanceAccountTo());
            Account from = new Account(balanceDto.getAccountIdFrom(), balanceDto.getBalanceAccountFrom());
            balancesService.upsert(List.of(new Account[]{to, from}));
        } catch (Exception e) {
            System.out.println("error when trying to process kafka's topic 'balances': " + e.getMessage());
        }
    }
}
