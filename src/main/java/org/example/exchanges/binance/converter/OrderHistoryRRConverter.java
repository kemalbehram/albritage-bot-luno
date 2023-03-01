package org.example.exchanges.binance.converter;

import com.google.gson.Gson;
import org.example.domain.enums.ExchangeStates;
import org.example.domain.enums.OrderSide;
import org.example.exchanges.binance.dto.AllOrdersResponseDto;
import org.example.exchanges.binance.model.OrderHistoryModel;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class OrderHistoryRRConverter {
    public static LinkedHashMap<String, OrderHistoryModel> orderHistoryConverter(List<AllOrdersResponseDto> allOrdersResponseDtoList) {
        LinkedHashMap<String, OrderHistoryModel> output = new LinkedHashMap<>();

        for(AllOrdersResponseDto allOrdersResponseDto : allOrdersResponseDtoList) {

            output.put(
                    allOrdersResponseDto.getOrderId(),
                    new OrderHistoryModel(
                            allOrdersResponseDto.getSymbol(),
                            allOrdersResponseDto.getOrderId(),
                            stateConvert(allOrdersResponseDto.getStatus()),
                            sideConvert(allOrdersResponseDto.getSide()),
                            allOrdersResponseDto.getUpdateTime()
                    )
            );
        }
        return output;
    }

    private static ExchangeStates stateConvert(String state) {
        switch (state) {
            case "FILLED" -> {
                return ExchangeStates.COMPLETED;
            }
            case "NEW" -> {
                return ExchangeStates.WAITING;
            }
        }
        return ExchangeStates.FAILED;
    }

    private static OrderSide sideConvert(String side) {
        switch (side) {
            case "BUY" -> {
                return OrderSide.BUY;
            }
            case "SELL" -> {
                return OrderSide.SELL;
            }
        }
        return null;
    }
}
