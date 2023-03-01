package org.example.exchanges.binance.converter;

import org.example.exchanges.binance.dto.DepositAddressResponseDto;
import org.example.exchanges.binance.model.AddressModel;

public class AddressConverter {
    public static AddressModel addressConverter(DepositAddressResponseDto depositAddressResponseDto) {
        return new AddressModel(
                depositAddressResponseDto.getAddress(),
                depositAddressResponseDto.getCoin(),
                depositAddressResponseDto.getTag()
        );
    }
}
