package org.example.exchanges.binance.converter;

import org.example.exchanges.binance.dto.AccountInformationDto;
import org.example.exchanges.binance.model.TradeFeeModel;


public class TradeFeeConverter {
    public static TradeFeeModel TradeFeeConverter(AccountInformationDto accountInformationDto) {
        return new TradeFeeModel(
                accountInformationDto.getCommissionRates().getTaker()
        );
    }

}
