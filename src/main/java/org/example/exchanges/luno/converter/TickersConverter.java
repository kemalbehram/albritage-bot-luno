package org.example.exchanges.luno.converter;

import org.example.exchanges.luno.dto.TickersForAllCurrenciesDto;
import org.example.exchanges.luno.model.TickerModel;

import java.math.BigDecimal;
import java.util.LinkedHashMap;

public class TickersConverter {
    public static LinkedHashMap<String, TickerModel> tickersConverter(TickersForAllCurrenciesDto dto) {
        LinkedHashMap<String, TickerModel> output = new LinkedHashMap<>();

        for(TickersForAllCurrenciesDto.Ticker ticker : dto.getTickers()) {
            output.put(
                    ticker.getPair(),
                    new TickerModel(
                            ticker.getPair(),
                            Double.parseDouble(ticker.getRolling_24_hour_volume()),
                            new BigDecimal(ticker.getAsk())
                    )
            );
        }

        return output;
    }
}
