package org.example.exchanges.binance.converter;

import org.example.exchanges.binance.dto.AllCoinsInformationDto;
import org.example.exchanges.binance.model.WithdrawFeeModel;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class WithdrawFeeConverter {
    public static LinkedHashMap<String, WithdrawFeeModel> withdrawFeeConverter(List<AllCoinsInformationDto> allCoinsInformationDtoList) {
        LinkedHashMap<String, WithdrawFeeModel> output = new LinkedHashMap<>();

        for(AllCoinsInformationDto allCoinsInformationDto : allCoinsInformationDtoList) {

            output.put(
                    allCoinsInformationDto.getCoin(),
                    new WithdrawFeeModel (
                            allCoinsInformationDto.getCoin(),
                            allCoinsInformationDto.getFree(),
                            networkListConverter(allCoinsInformationDto.getNetworkList())
                    )
            );
        }

        return output;
    }

    public static List<WithdrawFeeModel.Network> networkListConverter(List<AllCoinsInformationDto.Network> networkList) {
        LinkedList<WithdrawFeeModel.Network> outputNetworks = new LinkedList<>();

        for(AllCoinsInformationDto.Network network : networkList) {
            outputNetworks.add(
                    new WithdrawFeeModel.Network(
                            network.getNetwork(),
                            network.getWithdrawFee(),
                            network.getEstimatedArrivalTime()
                    )
            );
        }

        return outputNetworks;
    }
}
