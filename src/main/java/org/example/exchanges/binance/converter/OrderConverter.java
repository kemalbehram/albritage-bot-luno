package org.example.exchanges.binance.converter;

import org.example.exchanges.binance.dto.NewOrderResponseDto;
import org.example.exchanges.binance.model.OrderModel;

public class OrderConverter {
    public static OrderModel orderConverter(NewOrderResponseDto newOrderResponseDto) {
        return new OrderModel(
                newOrderResponseDto.getSymbol(),
                newOrderResponseDto.getOrderId(),
                newOrderResponseDto.getTransactTime()
        );
    }
}
