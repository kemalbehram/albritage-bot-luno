package org.example.viewmodels.ExchangeViewmodels;

import org.example.domain.enums.ExchangeEnums;
import org.example.domain.models.MainStateModel;
import org.example.domain.models.MainStateModel.ExchangeData;
import org.example.exchanges.binance.model.*;
import org.example.exchanges.binance.repository.BinanceRepository;
import org.example.viewmodels.MainViewModel;

import java.util.*;


public class BinanceViewModel {
    public static MainStateModel.ExchangeData initialExchangeData() {
        TradeFeeModel tradeFee = BinanceRepository.tradeFee();
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

        Map<String, WithdrawFeeModel> binanceWithdrawFeeData = BinanceRepository.withdrawFee();
        Map<String, TickerModel> binanceTickerData = BinanceRepository.tickers();
        Map<String, CoinInformationModel> coinsData = BinanceRepository.coinInformation();

        for(WithdrawFeeModel withdrawFee : binanceWithdrawFeeData.values()) {
            List<String> coins = coinsData.keySet().stream().filter(coin -> {
                if (coin.contains(withdrawFee.getSymbol())) {
                    return coin.substring(0, withdrawFee.getSymbol().length()).equals(withdrawFee.getSymbol());
                } else {return false;}
            }).toList();
            LinkedHashMap<String, MainStateModel.ExchangeData.CoinData.Blockchain> blockchains = BlockchainConverter(withdrawFee.getNetworkList(), withdrawFee.getSymbol());

            for(String coin : coins) {
                TickerModel ticker = binanceTickerData.get(coin.replace("_",""));
                ExchangeData.CoinData.OrderBook orderBook = BinanceRepository.orderBook(coin.replace("_",""));
                initialCoinDataList.put(
                        coin,
                        new MainStateModel.ExchangeData.CoinData(
                                coin,
                                ticker.getPrice(),
                                ticker.getVolume(),
                                withdrawFee.getBalance(),
                                coinsData.get(coin).getFilters().getMinQty(),
                                orderBook,
                                blockchains
                        )
                );
            }
        }
        return initialCoinDataList;
    }

    public static LinkedHashMap<String, MainStateModel.ExchangeData.CoinData.Blockchain> BlockchainConverter(
            List<WithdrawFeeModel.Network> blockchainList,
            String coin
    ) {
        LinkedHashMap<String, MainStateModel.ExchangeData.CoinData.Blockchain> output = new LinkedHashMap<>();

        for(WithdrawFeeModel.Network blockchain : blockchainList) {
           AddressModel address = null;
                   //BinanceRepository.address(coin, blockchain.getNetwork());

            //todo add tag
            if (address != null) {
                output.put(
                        blockchain.getNetwork(),
                        new MainStateModel.ExchangeData.CoinData.Blockchain(
                                blockchain.getNetwork(),
                                address.getAddress(),
                                address.getTag(),
                                blockchain.getFee(),
                                blockchain.getWithdrawTime()
                        )
                );
            } else {
                output.put(
                        blockchain.getNetwork(),
                        new MainStateModel.ExchangeData.CoinData.Blockchain(
                                blockchain.getNetwork(),
                                "null",
                                "null",
                                blockchain.getFee(),
                                blockchain.getWithdrawTime()
                        )
                );
            }
        }

        return output;
    }

    public static Map<ExchangeEnums, MainStateModel.ExchangeData> updateTicker(String symbol) {
        TickerModel ticker = BinanceRepository.ticker(symbol);

        MainStateModel.ExchangeData exchangeData = MainViewModel.state().getExchangeData().get(ExchangeEnums.BINANCE);
        Map<String, MainStateModel.ExchangeData.CoinData> output = exchangeData.getCoinsData();

        if (ticker != null) {
            MainStateModel.ExchangeData.CoinData currentTicker = output.get(symbol);
            ExchangeData.CoinData.OrderBook orderBook = BinanceRepository.orderBook(symbol);
            output.put(
                    currentTicker.getSymbol(),
                    new MainStateModel.ExchangeData.CoinData(
                            currentTicker.getSymbol(),
                            ticker.getPrice(),
                            ticker.getVolume(),
                            currentTicker.getBalance(),
                            currentTicker.getMinQty(),
                            orderBook,
                            currentTicker.getBlockchainsAndFees()
                    )
            );
        }

        return Map.of(
                ExchangeEnums.BINANCE,
                new MainStateModel.ExchangeData(
                        exchangeData.getTakerFee(),
                        output
                )
        );
    }

    public static MainStateModel.ExchangeData updateCoinBalance(String symbol) {
        MainStateModel.ExchangeData exchangeData = MainViewModel.state().getExchangeData().get(ExchangeEnums.BINANCE);
        Map<String, MainStateModel.ExchangeData.CoinData> output = exchangeData.getCoinsData();

        Map<String, WithdrawFeeModel> coinWithdrawFeeData = BinanceRepository.withdrawFee();

        System.out.println("bi balance have key "+coinWithdrawFeeData.containsKey(symbol.split("_")[0]));

        if (coinWithdrawFeeData.containsKey(symbol.split("_")[0])) {
            System.out.println("balances from api" + coinWithdrawFeeData.get(symbol.split("_")[0]).getBalance());
            output.put(
                    symbol,
                    new MainStateModel.ExchangeData.CoinData(
                            symbol,
                            exchangeData.getCoinsData().get(symbol).getPrice(),
                            exchangeData.getCoinsData().get(symbol).getVolume(),
                            coinWithdrawFeeData.get(symbol.split("_")[0]).getBalance(),
                            exchangeData.getCoinsData().get(symbol).getMinQty(),
                            exchangeData.getCoinsData().get(symbol).getOrderBook(),
                            exchangeData.getCoinsData().get(symbol).getBlockchainsAndFees()
                    )
            );
        }

        return new MainStateModel.ExchangeData(
                exchangeData.getTakerFee(),
                output
        );
    }

}
