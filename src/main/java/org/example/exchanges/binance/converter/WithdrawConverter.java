package org.example.exchanges.binance.converter;

import org.example.exchanges.binance.dto.WithdrawResponseDto;
import org.example.exchanges.binance.model.WithdrawModel;

public class WithdrawConverter {
    public static WithdrawModel withdrawConverter(WithdrawResponseDto withdrawResponseDto) {
        return new WithdrawModel(
                withdrawResponseDto.getId()
        );
    }
}
