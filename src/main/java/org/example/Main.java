package org.example;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.enums.ExchangeEnums;
import org.example.domain.models.MainStateModel;
import org.example.exchanges.binance.model.WithdrawFeeModel;
import org.example.exchanges.binance.repository.BinanceRepository;
import org.example.local.DataStorage;
import org.example.utils.RoundToMinQty;
import org.example.viewmodels.MainViewModel;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.example.di.Logger.ResultLogger.myLog;
import static org.example.viewmodels.MainViewModel.state;


@Slf4j
public class Main {

    public static void main(String[] args) {
        MainViewModel.initialCoinData();

        List<MainStateModel.ExchangeData.CoinData> startCoinList = MainViewModel.generateStartCoinList();
        Map<ExchangeEnums, MainStateModel.ExchangeData> endCoinList = MainViewModel.generateEndCoinList();
        //cycling throw start exchange coins
        for (MainStateModel.ExchangeData.CoinData startCoin : startCoinList) {
            String[] startBaseQuoteCurrency = startCoin.getSymbol().split("_");
            //cycling throw end exchanges and comparing prices
            for (ExchangeEnums endExchange : endCoinList.keySet()) {
                MainViewModel.updateInitialState("", "", state().getStatExchange(), endExchange);
                for (MainStateModel.ExchangeData.CoinData endCoin : state().getExchangeData().get(endExchange).getCoinsData().values()) {
                    myLog.info("=====================================================================================================================");

                    String[] endBaseQuoteCurrency = endCoin.getSymbol().split("_");
                    if (
                            startBaseQuoteCurrency[0].equals(endBaseQuoteCurrency[0])
                    ) {
                        MainViewModel.updateInitialState(startCoin.getSymbol(), endCoin.getSymbol(), state().getStatExchange(), endExchange);
                        MainViewModel.updateStartEndExchangeTicker(startCoin.getSymbol());

                        BigDecimal profit = MainViewModel.profitAfterFees(startCoin.getSymbol(), endCoin.getSymbol());
                        myLog.info(startCoin.getSymbol());
                        myLog.info(endCoin.getSymbol());
                        myLog.info(profit.toString());

                        if (profit.compareTo(new BigDecimal("0.0")) > 0) {
                            MainViewModel.buyCoin();
                            MainViewModel.withdrawCoin();
                            MainViewModel.sellCoin();
                        }
                    }

                    myLog.info("=====================================================================================================================");
                }
            }
        }
    }
}

