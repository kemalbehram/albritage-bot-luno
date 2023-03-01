package org.example.utils;

import org.example.domain.enums.ExchangeEnums;
import org.example.viewmodels.MainViewModel;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class RoundToMinQty {

    public static BigDecimal roundToMinQty(BigDecimal coinAmount, String coin, ExchangeEnums exchange) {
        Integer filterLength = MainViewModel.state().getExchangeData()
                .get(exchange)
                .getCoinsData().get(coin)
                .getMinQty().toString().indexOf("1");

        System.out.println("filterLength " + filterLength );
        System.out.println(coinAmount);

        try {
            if (filterLength < coinAmount.toString().length() && filterLength > 1) {
                return coinAmount.setScale(filterLength-1, RoundingMode.DOWN);
            } else {
                return coinAmount.setScale(0, RoundingMode.DOWN);
            }
        } catch (Exception e) {
            return coinAmount.setScale(0, RoundingMode.DOWN);
        }
    }

}
