package org.example.exchanges.luno.converter;

import org.example.exchanges.luno.dto.getBalance.Balance;
import org.example.exchanges.luno.dto.getBalance.GetBalanceDto;
import org.example.exchanges.luno.model.BalanceModel;

import java.math.BigDecimal;
import java.util.LinkedHashMap;

public class BalancesConverter {
    public static LinkedHashMap<String, BalanceModel> balanceConverter(GetBalanceDto dto) {
        LinkedHashMap<String, BalanceModel> output = new LinkedHashMap<>();

        for(Balance balance : dto.balance) {
            output.put(
                    balance.asset,
                    new BalanceModel(
                            balance.asset,
                            new BigDecimal(balance.balance)
                    )
            );
        }

        return output;
    }
}
