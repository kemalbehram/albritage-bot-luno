package org.example.exchanges.luno.converter;

import org.example.exchanges.luno.dto.GetFeeInformationDto;
import org.example.exchanges.luno.model.TradeFeeModel;

public class TradeFeeConverter {
    public static TradeFeeModel tradeFeeConverter(GetFeeInformationDto dto) {
        return new TradeFeeModel(
                dto.getTaker_fee()
        );
    }
}
