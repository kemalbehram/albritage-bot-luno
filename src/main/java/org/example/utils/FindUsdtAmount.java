package org.example.utils;

import org.example.domain.enums.ExchangeEnums;
import org.example.domain.models.MainStateModel;
import org.example.viewmodels.MainViewModel;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class FindUsdtAmount {
    public static BigDecimal getUsdtAmount(ExchangeEnums exchange) {
        List<MainStateModel.ExchangeData.CoinData> usdtCoinList = new java.util.ArrayList<>(MainViewModel.state().getExchangeData()
                .get(exchange).getCoinsData().values().stream()
                .filter(s -> s.getSymbol().split("_")[0].contains("USDT")).toList());

        System.out.println(usdtCoinList);

        usdtCoinList.sort(Comparator.comparing(MainStateModel.ExchangeData.CoinData::getPrice));

        if (!usdtCoinList.isEmpty()) {
            return usdtCoinList.get(0).getBalance();
        } else {
            return null;
        }
    }

}
