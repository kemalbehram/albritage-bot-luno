package org.example.exchanges.luno.converter;

import org.example.exchanges.luno.dto.getMarketsInfo.GetMarketsInfoDto;
import org.example.exchanges.luno.dto.getMarketsInfo.Market;
import org.example.exchanges.luno.model.CurrenciesInformationModel;

import java.math.BigDecimal;
import java.util.LinkedHashMap;

public class CurrenciesInformationConverter {
    public static LinkedHashMap<String, CurrenciesInformationModel> currenciesInformationConverter(GetMarketsInfoDto dto) {
        LinkedHashMap<String, CurrenciesInformationModel> output = new LinkedHashMap<>();

        for(Market market : dto.markets) {
            String symbol = market.market_id;
            output.put(
                    symbol,
                    new CurrenciesInformationModel(
                            symbol,
                            market.base_currency,
                            market.counter_currency,
                            new BigDecimal(market.price_scale)
                    )
            );
        }

        return output;
    }
}
