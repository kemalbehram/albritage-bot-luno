package org.example.exchanges.luno.converter;

import org.example.exchanges.luno.dto.EstimateSendFeesDto;
import org.example.exchanges.luno.model.WithdrawFeeModel;

import java.math.BigDecimal;

public class WithdrawFeeConverter {
    public static WithdrawFeeModel withdrawFeeConverter(EstimateSendFeesDto dto) {
        return new WithdrawFeeModel(
                dto.getCurrency(),
                new BigDecimal(dto.getFee())
        );
    }
}
