package org.example.viewmodels.ExchangeViewmodels;

import org.example.domain.enums.ExchangeEnums;
import org.example.domain.models.MainStateModel;
import org.example.exchanges.luno.model.*;
import org.example.exchanges.luno.repository.LunoRepository;
import org.example.utils.AddressToBlockchain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedHashMap;

import java.util.Map;

public class LunoViewModel {

    public static MainStateModel.ExchangeData initialExchangeData() {
        TradeFeeModel tradeFee = LunoRepository.TradeFee();
        if (tradeFee != null) {
            return new MainStateModel.ExchangeData(
                    tradeFee.getTakerFee(),
                    initialCoinData()
            );
        } else {
            return new MainStateModel.ExchangeData(
                    0.5,
                    initialCoinData()
            );
        }
    }

    public static LinkedHashMap<String, MainStateModel.ExchangeData.CoinData> initialCoinData() {
        LinkedHashMap<String, MainStateModel.ExchangeData.CoinData> initialCoinDataList = new LinkedHashMap<>();

        Map<String, CurrenciesInformationModel> coinsData = LunoRepository.CurrenciesInformation();
        Map<String, TickerModel> tickers = LunoRepository.Tickers();
        Map<String, BalanceModel> balances = LunoRepository.Balances();

        for (CurrenciesInformationModel coin : coinsData.values().stream().toList()) {
            System.out.println(coin.getSymbol());
           if (tickers.containsKey(coin.getSymbol())) {
               TickerModel ticker = tickers.get(coin.getSymbol());
               BigDecimal balance = (balances.containsKey(coin.getBaseCurrency())) ? balances.get(coin.getBaseCurrency()).getBalance() : new BigDecimal("0");
               //request
               MainStateModel.ExchangeData.CoinData.OrderBook orderBook = LunoRepository.OrderBook(coin.getSymbol());
               AddressModel address = LunoRepository.Address(coin.getBaseCurrency());
               if (address == null) {
                   address = LunoRepository.CreateAddress(coin.getBaseCurrency());
               }
               WithdrawFeeModel withdrawFee = LunoRepository.WithdrawFee(new BigDecimal(25), (!coin.getBaseCurrency().equals("XRP")) ? coin.getBaseCurrency() : "XBT", (address != null) ? address.getAddress() : "");
               String blockchain = AddressToBlockchain.addressToBlockchain((address != null) ? address.getAddress() : "");

               initialCoinDataList.put(
                       coin.getBaseCurrency()+"_"+coin.getQuoteCurrency(),
                       new MainStateModel.ExchangeData.CoinData(
                               coin.getBaseCurrency()+"_"+coin.getQuoteCurrency(),
                               ticker.getPrice(),
                               ticker.getVolume(),
                               balance,
                               amountLengthToMinQty(coin.getAmountLength()),
                               orderBook,
                               Map.of(
                                       (blockchain != null) ? blockchain : "null",
                                       new MainStateModel.ExchangeData.CoinData.Blockchain(
                                               (blockchain != null) ? blockchain : "null",
                                               (address != null) ? address.getAddress() : "null",
                                               "null",
                                               (withdrawFee != null) ? withdrawFee.getFee() : new BigDecimal("999999"),
                                               20.0
                                       )
                               )
                       )
               );
           }
        }

        return initialCoinDataList;
    }

    public static BigDecimal amountLengthToMinQty(BigDecimal length) {
        if (length.intValue() > 0) {
            return new BigDecimal(("0.000000000000000000000000".substring(0, length.intValue()-1)+"1"));
        }
        return new BigDecimal("1");
    }






    public static Map<ExchangeEnums, MainStateModel.ExchangeData> updateTicker(String symbol) {
        return new LinkedHashMap<>();
    }
    public static MainStateModel.ExchangeData updateCoinBalance(String symbol) {
        return null;
    }
}
