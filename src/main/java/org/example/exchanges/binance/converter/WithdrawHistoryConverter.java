package org.example.exchanges.binance.converter;

import org.example.domain.enums.ExchangeStates;
import org.example.exchanges.binance.dto.WithdrawHistoryDto;
import org.example.exchanges.binance.model.WithdrawHistoryModel;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class WithdrawHistoryConverter {
    public static LinkedHashMap<String, WithdrawHistoryModel> withdrawHistoryConverter(List<WithdrawHistoryDto> withdrawHistoryDtoList) {
        LinkedHashMap<String, WithdrawHistoryModel> withdrawals = new LinkedHashMap<>();

        for(WithdrawHistoryDto withdrawHistoryDto : withdrawHistoryDtoList) {
            withdrawals.put(
                    withdrawHistoryDto.getId(),
                    new WithdrawHistoryModel(
                            withdrawHistoryDto.getId(),
                            withdrawHistoryDto.getAmount(),
                            withdrawHistoryDto.getTransactionFee(),
                            stateConvert(withdrawHistoryDto.getStatus()),
                            StringToTimestamp(withdrawHistoryDto.getApplyTime())
                    )
            );
        }

        return withdrawals;
    }
    private static ExchangeStates stateConvert(Integer state) {
        switch (state) {
            case 0, 2, 4 -> {
                return ExchangeStates.WAITING;
            }
            case 1, 3, 5 -> {
                return ExchangeStates.FAILED;
            }
            case 6 -> {
                return ExchangeStates.COMPLETED;
            }
        }
        return ExchangeStates.FAILED;
    }

    private static long StringToTimestamp(String time) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date parsedDate = dateFormat.parse(time);
            return parsedDate.toInstant().getEpochSecond();
        } catch (Exception e) {
            return 000000000;
        }
    }

}
