package org.example.utils;

import org.example.domain.enums.ExchangeEnums;
import org.example.domain.models.MainStateModel;
import org.example.viewmodels.MainViewModel;

import java.util.*;

public class FindCoinBlockchain {
    public static MainStateModel.ExchangeData.CoinData.Blockchain findCoinBlockchain(String coin, ExchangeEnums exchange) {
        List<MainStateModel.ExchangeData.CoinData.Blockchain> blockchains = new ArrayList<>(MainViewModel.state().getExchangeData()
                .get(exchange).getCoinsData()
                .get(coin).getBlockchainsAndFees().values().stream()
                .filter(s -> s.getWithdrawTime() < 40)
                .filter(s -> !s.getAddress().equals("null")).toList());

        blockchains.sort(Comparator.comparing(MainStateModel.ExchangeData.CoinData.Blockchain::getFee));

        if (!blockchains.isEmpty()) {
            return blockchains.get(0);
        } else  {
            return null;
        }

    }
}
