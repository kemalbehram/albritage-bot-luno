package org.example.utils;

import org.example.Configure;
import org.example.domain.enums.ExchangeEnums;
import org.example.domain.enums.OrderSide;
import org.example.domain.models.MainStateModel;
import org.example.viewmodels.MainViewModel;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.example.di.Logger.ResultLogger.myLog;
import static org.example.viewmodels.MainViewModel.state;

public class AlbritageCalculation {
    public static BigDecimal calculateProfit(
            BigDecimal tradeAmount,
            MainStateModel.ExchangeData.CoinData startCoin,
            MainStateModel.ExchangeData.CoinData endCoin,
            MainStateModel.ExchangeData.CoinData.Blockchain startBlockchain
    ) {
        MainStateModel.ExchangeData startExchange = state().getExchangeData().get(state().getStatExchange());
        MainStateModel.ExchangeData endExchange = state().getExchangeData().get(state().getEndExchange());


        BigDecimal buyFeesProcentage = new BigDecimal(Configure.SLIPPAGE_PROCENT+startExchange.getTakerFee());
        BigDecimal sellFeesProcentage = new BigDecimal(Configure.SLIPPAGE_PROCENT+endExchange.getTakerFee());

        try {
            BigDecimal avaibleAmount = MainViewModel.getCoinQty(
                    state().getEndExchange(),
                    endCoin,
                    endCoin.getPrice().divide(new BigDecimal("1.1"), RoundingMode.HALF_EVEN),
                    OrderSide.BUY
            ).multiply(endCoin.getPrice());

            myLog.info("BI price : "+ startCoin.getPrice().toString());
            myLog.info("LUNO price : "+ endCoin.getPrice().toString());

            BigDecimal newTradeAmount = (avaibleAmount.compareTo(tradeAmount) > 0) ? tradeAmount : avaibleAmount;
            MainViewModel.setTradeAmount(newTradeAmount);

            myLog.info("tradeAmount : "+newTradeAmount.toString());

            BigDecimal buyQuantityInklFees = minusProcentage(buyFeesProcentage, newTradeAmount.divide(startCoin.getPrice(), RoundingMode.HALF_EVEN))
                    .subtract(startBlockchain.getFee());

            BigDecimal sellAmountInklFees = minusProcentage(sellFeesProcentage, buyQuantityInklFees.multiply(endCoin.getPrice()));

            if (currencyConverter(
                    endCoin.getSymbol().split("_")[1],
                    startCoin.getSymbol().split("_")[1],
                    buyQuantityInklFees.multiply(endCoin.getPrice())
            ).compareTo(new BigDecimal("0.0")) <= 0.0) {
                return new BigDecimal("0.0");
            } else {
                return sellAmountInklFees.subtract(newTradeAmount);
            }
        } catch (Exception e) {
            myLog.info(e.toString());
            return new BigDecimal("0.0");
        }
    }

    private static BigDecimal calculateDifference(
            BigDecimal tradeAmount,
            MainStateModel.ExchangeData.CoinData startCoin,
            MainStateModel.ExchangeData.CoinData endCoin,
            MainStateModel.ExchangeData.CoinData.Blockchain startBlockchain
    ) {
        MainStateModel.ExchangeData startExchange = state().getExchangeData().get(state().getStatExchange());
        MainStateModel.ExchangeData endExchange = state().getExchangeData().get(state().getEndExchange());


        BigDecimal buyFeesProcentage = new BigDecimal(Configure.SLIPPAGE_PROCENT+startExchange.getTakerFee());
        BigDecimal sellFeesProcentage = new BigDecimal(Configure.SLIPPAGE_PROCENT+endExchange.getTakerFee());

        try {
            BigDecimal buyQuantityInklFees = minusProcentage(buyFeesProcentage, tradeAmount.divide(startCoin.getPrice(), RoundingMode.HALF_EVEN))
                    .subtract(startBlockchain.getFee());

            BigDecimal buyAmount = (endCoin.getSymbol().contains("USD")) ? buyQuantityInklFees.multiply(endCoin.getPrice()) : currencyConverter(
                    endCoin.getSymbol().split("_")[1],
                    startCoin.getSymbol().split("_")[1],
                    buyQuantityInklFees.multiply(endCoin.getPrice())
            );

            BigDecimal sellAmountInklFees = minusProcentage(sellFeesProcentage, buyAmount);

            BigDecimal dif = ((sellAmountInklFees.subtract(tradeAmount)).multiply(new BigDecimal(100))).divide(tradeAmount, RoundingMode.HALF_EVEN);

            return dif;
        } catch (Exception e) {
            return new BigDecimal("0.0");
        }
    }

    private static BigDecimal currencyConverter(
            String currentCurrency,
            String newCurrency,
            BigDecimal amount
    ) {
        if (state().getExchangeData().get(ExchangeEnums.BINANCE).getCoinsData().containsKey(currentCurrency+"_"+newCurrency)) {
            myLog.info("SUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUCCES");
            MainStateModel.ExchangeData.CoinData currency = state().getExchangeData().get(ExchangeEnums.BINANCE).getCoinsData().get(currentCurrency+"_"+newCurrency);
            return amount.multiply(currency.getPrice());
        }
        return new BigDecimal("0.0");
    }

    private static BigDecimal minusProcentage(
            BigDecimal procentage,
            BigDecimal value
    ) {
        BigDecimal procentageOfValue = procentage.multiply(value).divide(new BigDecimal(100), RoundingMode.HALF_EVEN);

        return value.subtract(procentageOfValue);
    }
}
