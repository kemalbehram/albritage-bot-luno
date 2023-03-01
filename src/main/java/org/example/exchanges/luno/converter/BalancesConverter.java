package org.example.exchanges.luno.converter;

import org.example.exchanges.luno.dto.GetBalanceDto;
import org.example.exchanges.luno.model.BalanceModel;

import java.math.BigDecimal;
import java.util.LinkedHashMap;

public class BalancesConverter {
    public static LinkedHashMap<String, BalanceModel> balanceConverter(GetBalanceDto dto) {
        LinkedHashMap<String, BalanceModel> output = new LinkedHashMap<>();

        for(GetBalanceDto.Balance balance : dto.getBalance()) {
            output.put(
                    balance.getAsset(),
                    new BalanceModel(
                            balance.getAsset(),
                            new BigDecimal(balance.getBalance())
                    )
            );
        }

        return output;
    }
}
