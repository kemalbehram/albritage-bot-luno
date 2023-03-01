package org.example.exchanges.luno.converter;

import org.example.exchanges.luno.dto.GetReceiveAddressDto;
import org.example.exchanges.luno.model.AddressModel;

public class AddressConverter {
    public static AddressModel addressConverter(GetReceiveAddressDto dto) {
        return new AddressModel(
                dto.getAsset(),
                dto.getAddress()
        );
    }
}
