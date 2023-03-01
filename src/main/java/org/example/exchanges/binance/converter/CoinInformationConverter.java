package org.example.exchanges.binance.converter;

import org.example.exchanges.binance.dto.ExchangeInformationDto;
import org.example.exchanges.binance.model.CoinInformationModel;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class CoinInformationConverter {

    public static LinkedHashMap<String, CoinInformationModel> coinInformationConverter(List<ExchangeInformationDto.Symbol> SymbolList) {
        LinkedHashMap<String, CoinInformationModel> outputNetworks = new LinkedHashMap<>();

        for(ExchangeInformationDto.Symbol symbol : SymbolList) {
            outputNetworks.put(
                    symbol.getBaseAsset()+"_"+symbol.getQuoteAsset(),
                    new CoinInformationModel(
                            symbol.getSymbol(),
                            symbol.getBaseAsset(),
                            symbol.getQuoteAsset(),
                            lotSizeConverter(symbol.getFilters())
                    )
            );
        }
        return outputNetworks;
    }

    private static CoinInformationModel.LotSize lotSizeConverter(List<Map<String, String>> filters) {
        Map lotSize = filters.stream().filter(stringStringMap -> stringStringMap.values().contains("LOT_SIZE")).toList().get(0);
        return new CoinInformationModel.LotSize(
                new BigDecimal(lotSize.get("minQty").toString()),
                new BigDecimal(lotSize.get("maxQty").toString()),
                new BigDecimal(lotSize.get("stepSize").toString())
        );
    }

}