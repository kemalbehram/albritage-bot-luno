package org.example.exchanges.luno.converter;

import org.example.domain.enums.ExchangeStates;
import org.example.exchanges.luno.dto.ListWithdrawalRequestsDto;
import org.example.exchanges.luno.model.WithdrawHistoryModel;

import java.util.LinkedHashMap;

public class WithdrawHistoryConverter {
    public static LinkedHashMap<String, WithdrawHistoryModel> withdrawHistoryConverter(ListWithdrawalRequestsDto dto) {
        LinkedHashMap<String, WithdrawHistoryModel> output = new LinkedHashMap<>();

        for(ListWithdrawalRequestsDto.Withdrawal withdrawal : dto.getWithdrawals()) {
            output.put(
                    withdrawal.getCurrency(),
                    new WithdrawHistoryModel(
                            withdrawal.getCurrency(),
                            withdrawal.getId(),
                            Double.parseDouble(withdrawal.getAmount()),
                            Double.parseDouble(withdrawal.getFee()),
                            ExchangeStatesConverter(withdrawal.getStatus()),
                            Long.parseLong(withdrawal.getCreated_at())
                    )
            );
        }

        return output;
    }

    private static ExchangeStates ExchangeStatesConverter(String state) {
        switch (state) {
            case "PENDING" -> {
                return ExchangeStates.WAITING;
            }
            case "COMPLETE" -> {
                return ExchangeStates.COMPLETED;
            }
        }
        return ExchangeStates.FAILED;
    }
}
