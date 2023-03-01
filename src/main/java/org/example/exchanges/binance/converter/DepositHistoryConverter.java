package org.example.exchanges.binance.converter;

import org.example.domain.enums.ExchangeStates;
import org.example.exchanges.binance.dto.DepositHistoryDto;
import org.example.exchanges.binance.model.DepositHistoryModel;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class DepositHistoryConverter {

    public static LinkedHashMap<String, DepositHistoryModel> depositHistoryConverter(List<DepositHistoryDto> depositHistoryDtoList) {
        LinkedHashMap<String, DepositHistoryModel> depositHistory = new LinkedHashMap<>();

        for(DepositHistoryDto depositHistoryDto : depositHistoryDtoList) {
            depositHistory.put(
                    depositHistoryDto.getCoin(),
                    new DepositHistoryModel(
                            depositHistoryDto.getId(),
                            depositHistoryDto.getAmount(),
                            depositHistoryDto.getCoin(),
                            stateConvert(depositHistoryDto.getStatus()),
                            depositHistoryDto.getInsertTime()
                    )
            );
        }

        return depositHistory;
    }

    private static ExchangeStates stateConvert(Integer state) {
        switch (state) {
            case 1 -> {
                return ExchangeStates.COMPLETED;
            }
            case 0 -> {
                return ExchangeStates.WAITING;
            }
        }
        return ExchangeStates.FAILED;
    }
}
