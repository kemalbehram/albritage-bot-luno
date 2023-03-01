package org.example.exchanges.luno.converter;

import org.example.domain.enums.ExchangeStates;
import org.example.domain.enums.OrderSide;
import org.example.exchanges.luno.model.OrderHistoryModel;
import org.example.exchanges.luno.dto.ListOrdersDto;

import java.math.BigDecimal;
import java.util.LinkedHashMap;

public class OrderHistoryConverter {
    public static LinkedHashMap<String, OrderHistoryModel> orderHistoryConverter(ListOrdersDto dto) {
        LinkedHashMap<String, OrderHistoryModel> output = new LinkedHashMap<>();

        for(ListOrdersDto.Order order : dto.getOrders()) {
            String symbol = order.getBase()+"_"+order.getCounter();
            output.put(
                    symbol,
                    new OrderHistoryModel(
                            symbol,
                            ExchangeStatesConverter(order.getState()),
                            order.getOrder_id(),
                            Long.parseLong(order.getCreation_timestamp()),
                            new BigDecimal(order.getFee_base()),
                            orderSideConverter(order.getType())
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
    private static OrderSide orderSideConverter(String side) {
        switch (side) {
            case "SELL" -> {
                return OrderSide.SELL;
            }
            case "BUY" -> {
                return OrderSide.BUY;
            }
        }
        return OrderSide.BUY;
    }
}
