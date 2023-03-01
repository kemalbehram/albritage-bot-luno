package org.example.exchanges.binance.converter;

import org.example.exchanges.binance.dto.Ticker24hResponseDto;
import org.example.exchanges.binance.model.TickerModel;

import java.util.LinkedHashMap;
import java.util.List;

public class TickerConverter {
    public static LinkedHashMap<String, TickerModel> tickerConverter(List<Ticker24hResponseDto> ticker24hDtoList) {
        LinkedHashMap<String, TickerModel> output = new LinkedHashMap<>();

        for(Ticker24hResponseDto ticker24hDto : ticker24hDtoList) {
            output.put(
                    ticker24hDto.getSymbol(),
                    new TickerModel(
                            ticker24hDto.getSymbol(),
                            ticker24hDto.getVolume(),
                            ticker24hDto.getLastPrice()
                    )
            );
        }

        return output;
    }
}
