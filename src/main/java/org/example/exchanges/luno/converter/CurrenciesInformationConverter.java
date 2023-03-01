package org.example.exchanges.luno.converter;

import org.example.exchanges.luno.dto.GetMarketsInfoDto;
import org.example.exchanges.luno.model.CurrenciesInformationModel;

import java.math.BigDecimal;
import java.util.LinkedHashMap;

public class CurrenciesInformationConverter {
    public static LinkedHashMap<String, CurrenciesInformationModel> currenciesInformationConverter(GetMarketsInfoDto dto) {
        LinkedHashMap<String, CurrenciesInformationModel> output = new LinkedHashMap<>();

        for(GetMarketsInfoDto.Market market : dto.getMarkets()) {
            String symbol = market.getMarket_id();
            output.put(
                    symbol,
                    new CurrenciesInformationModel(
                            symbol,
                            market.getBase_currency(),
                            market.getCounter_currency(),
                            new BigDecimal(market.getPrice_scale())
                    )
            );
        }

        return output;
    }
}
