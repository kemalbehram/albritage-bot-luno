package org.example.viewmodels;

import org.example.domain.enums.ExchangeEnums;
import org.example.domain.enums.ExchangeStates;
import org.example.domain.enums.OrderSide;
import org.example.domain.models.MainStateModel;
import org.example.exchanges.binance.model.OrderHistoryModel;
import org.example.exchanges.binance.model.OrderModel;
import org.example.exchanges.binance.model.WithdrawHistoryModel;
import org.example.exchanges.binance.model.WithdrawModel;
import org.example.exchanges.binance.repository.BinanceRepository;
import org.example.exchanges.luno.dto.PostMarketOrderDto;
import org.example.exchanges.luno.repository.LunoRepository;
import org.example.utils.AlbritageCalculation;
import org.example.utils.FindUsdtAmount;
import org.example.utils.RoundToMinQty;
import org.example.viewmodels.ExchangeViewmodels.BinanceViewModel;
import org.example.viewmodels.ExchangeViewmodels.LunoViewModel;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.example.di.Logger.ResultLogger.myLog;

public class MainViewModel {
    private static MainStateModel _state = new MainStateModel("","", new BigDecimal("0.0"), new MainStateModel.ExchangeData.CoinData.Blockchain("","","",new BigDecimal("100.0"),100.0), ExchangeEnums.BINANCE,ExchangeEnums.BINANCE,"","","", ExchangeStates.FAILED,ExchangeStates.FAILED,ExchangeStates.FAILED, Map.of());
    public static MainStateModel state() {
        return _state;
    }
    public static void updateInitialState(
            String currentCoin,
            String endCoin,
            ExchangeEnums startExchange,
            ExchangeEnums endExchange
    ) {
        _state = _state.copy(
                currentCoin,
                endCoin,
                _state.getTradeAmount(),
                _state.getCurrentBlockchain(),
                startExchange,
                endExchange,
                _state.getStatExchangeTradeId(),
                _state.getEndExchangeTradeId(),
                _state.getStatExchangeWithdrawId(),
                _state.getStatExchangeTradeState(),
                _state.getEndExchangeTradeState(),
                _state.getStatExchangeWithdrawState(),
                _state.getExchangeData()
        );
    }

    public static void setTradeAmount(BigDecimal amount) {
        _state = _state.copy(
                _state.getCurrentCoin(),
                _state.getEndCoin(),
                amount,
                _state.getCurrentBlockchain(),
                _state.getStatExchange(),
                _state.getEndExchange(),
                _state.getStatExchangeTradeId(),
                _state.getEndExchangeTradeId(),
                _state.getStatExchangeWithdrawId(),
                _state.getStatExchangeTradeState(),
                _state.getEndExchangeTradeState(),
                _state.getStatExchangeWithdrawState(),
                _state.getExchangeData()
        );
    }

    public static void initialCoinData() {
        LinkedHashMap<ExchangeEnums, MainStateModel.ExchangeData> ExchangeData = new LinkedHashMap<>(_state.getExchangeData());
        ExchangeData.put(
                ExchangeEnums.LUNO,
                LunoViewModel.initialExchangeData()
        );

        ExchangeData.put(
                ExchangeEnums.BINANCE,
                BinanceViewModel.initialExchangeData()
        );
        _state = _state.copy(
                _state.getCurrentCoin(),
                _state.getEndCoin(),
                _state.getTradeAmount(),
                _state.getCurrentBlockchain(),
                _state.getStatExchange(),
                _state.getEndExchange(),
                _state.getStatExchangeTradeId(),
                _state.getEndExchangeTradeId(),
                _state.getStatExchangeWithdrawId(),
                _state.getStatExchangeTradeState(),
                _state.getEndExchangeTradeState(),
                _state.getStatExchangeWithdrawState(),
                ExchangeData
        );
    }

    //_state.copy(
    //        _state.getCurrentCoin(),
    //        _state.getStatExchange(),
    //        _state.getEndExchange(),
    //        _state.getStatExchangeTradeId(),
    //        _state.getEndExchangeTradeId(),
    //        _state.getStatExchangeWithdrawId(),
    //        _state.getStatExchangeTradeState(),
    //        _state.getEndExchangeTradeState(),
    //        _state.getStatExchangeWithdrawState(),
    //        _state.getExchangeData()
    //);

    public static List<String> usdRatingList(ExchangeEnums exchange) {
        Map<String, MainStateModel.ExchangeData.CoinData> coinsData = state().getExchangeData().get(exchange).getCoinsData();
        Map<String, ArrayList<String>> currencies = new LinkedHashMap<>();
        for (String coin : coinsData.keySet()) {
            String quoteCurrency = coin.split("_")[1];
            if (currencies.containsKey(quoteCurrency)) {
                currencies.get(quoteCurrency).add(coin);
            } else {
                currencies.put(
                        quoteCurrency,
                        new ArrayList<>(List.of(coin))
                );
            }
        }
        ArrayList<String> output = new ArrayList<>(currencies.keySet().stream().toList());
        output.sort((o1, o2) -> Integer.compare(currencies.get(o2).size(), currencies.get(o1).size()));
        output.forEach(key -> {
            System.out.println(key +" : "+currencies.get(key).size());

        });
        return output.stream().filter(coin->coin.contains("USD")).toList();
    }

    public static MainStateModel.ExchangeData.CoinData findMostUsdCoin(ExchangeEnums exchange) {
        List<MainStateModel.ExchangeData.CoinData> output = new ArrayList<>(state().getExchangeData().get(exchange).getCoinsData().values().stream()
                .filter(s -> (s.getSymbol().split("_")[0].contains("USD"))).toList());

        output.sort((o1, o2) -> o2.getBalance().compareTo(o1.getBalance()));

        for (MainStateModel.ExchangeData.CoinData coin : output) {
            System.out.println("findMostUsdCoin " + coin);
        }

        //filter by usdt rating
        return output.get(0);
    }

    public static List<MainStateModel.ExchangeData.CoinData> generateStartCoinList() {
        ExchangeEnums startExchange = ExchangeEnums.BINANCE;
        BigDecimal mostUsdtAmount = new BigDecimal("0.0");

        for (ExchangeEnums exchangeKey : state().getExchangeData().keySet()) {
            BigDecimal usdtAmount = findMostUsdCoin(exchangeKey).getBalance();
            usdRatingList(exchangeKey);
            if (usdtAmount.compareTo(mostUsdtAmount) > 0) {
                mostUsdtAmount = usdtAmount;
                startExchange = exchangeKey;
            }
        }
        updateInitialState("","",startExchange, ExchangeEnums.BINANCE);

        Map<String, MainStateModel.ExchangeData.CoinData> startList = state().getExchangeData().get(startExchange).getCoinsData();

        System.out.println("generateStartCoinList "+startExchange);

        return filterStartList(startList).values().stream().toList();
    }

    public static Map<ExchangeEnums, MainStateModel.ExchangeData> generateEndCoinList() {
        LinkedHashMap<ExchangeEnums, MainStateModel.ExchangeData> output = new LinkedHashMap<>();

        for (ExchangeEnums key : state().getExchangeData().keySet().stream().filter(s -> s != state().getStatExchange()).toList()) {
            System.out.println("generateEndCoinList "+key);
            MainStateModel.ExchangeData currentExchangeData = state().getExchangeData().get(key);
            output.put(
                    key,
                    new MainStateModel.ExchangeData(
                            currentExchangeData.getTakerFee(),
                            filterEndList(currentExchangeData.getCoinsData())
                    )
            );
        }

        return output;
    }

    private static LinkedHashMap<String, MainStateModel.ExchangeData.CoinData> filterStartList(Map<String, MainStateModel.ExchangeData.CoinData> coinDataList) {
        LinkedHashMap<String, MainStateModel.ExchangeData.CoinData> output = new LinkedHashMap<>();


        for (MainStateModel.ExchangeData.CoinData coinData : coinDataList.values().stream().toList()) {
            for (MainStateModel.ExchangeData.CoinData.Blockchain blockchain : coinData.getBlockchainsAndFees().values()) {
                if (blockchain.getWithdrawTime() < 40) {
                    if (!blockchain.getAddress().equals("") || !blockchain.getAddress().equals("null")) {
                        if (coinData.getVolume() > 1000000) {
                            output.put(
                                    coinData.getSymbol(),
                                    coinData
                            );
                        }
                    }
                }
            }
        }

        return output;
    }
    private static LinkedHashMap<String, MainStateModel.ExchangeData.CoinData> filterEndList(Map<String, MainStateModel.ExchangeData.CoinData> coinDataList) {
        LinkedHashMap<String, MainStateModel.ExchangeData.CoinData> output = new LinkedHashMap<>();


        for (MainStateModel.ExchangeData.CoinData coinData : coinDataList.values().stream().toList()) {
            for (MainStateModel.ExchangeData.CoinData.Blockchain blockchain : coinData.getBlockchainsAndFees().values()) {
                if (blockchain.getWithdrawTime() < 40) {
                    if (!blockchain.getAddress().equals("") || !blockchain.getAddress().equals("null")) {
                        if (coinData.getVolume() > 1000) {
                            System.out.println(coinData.getSymbol()+": "+coinData.getVolume());
                            output.put(
                                    coinData.getSymbol(),
                                    coinData
                            );
                        }
                    }
                }
            }
        }

        return output;
    }

    public static void updateStartEndExchangeTicker(String symbol) {
        for (ExchangeEnums exchange : List.of(state().getStatExchange(), state().getEndExchange())) {
            switch (exchange) {
                case BINANCE -> {
                    Map<ExchangeEnums, MainStateModel.ExchangeData> newExchangeData = _state.getExchangeData();
                    newExchangeData.putAll(BinanceViewModel.updateTicker(symbol));
                    _state = _state.copy(
                            _state.getCurrentCoin(),
                            _state.getEndCoin(),
                            _state.getTradeAmount(),
                            _state.getCurrentBlockchain(),
                            _state.getStatExchange(),
                            _state.getEndExchange(),
                            _state.getStatExchangeTradeId(),
                            _state.getEndExchangeTradeId(),
                            _state.getStatExchangeWithdrawId(),
                            _state.getStatExchangeTradeState(),
                            _state.getEndExchangeTradeState(),
                            _state.getStatExchangeWithdrawState(),
                            newExchangeData
                    );
                }
            }
        }
    }

    public static void updateCoinBalance(String symbol) {
        for (ExchangeEnums exchange : List.of(state().getStatExchange(), state().getEndExchange())) {
            switch(exchange) {
                case BINANCE -> {
                    Map<ExchangeEnums, MainStateModel.ExchangeData> newCoinBalance = state().getExchangeData();
                    newCoinBalance.put(
                            ExchangeEnums.BINANCE,
                            BinanceViewModel.updateCoinBalance(symbol)
                    );
                    _state = _state.copy(
                            _state.getCurrentCoin(),
                            _state.getEndCoin(),
                            _state.getTradeAmount(),
                            _state.getCurrentBlockchain(),
                            _state.getStatExchange(),
                            _state.getEndExchange(),
                            _state.getStatExchangeTradeId(),
                            _state.getEndExchangeTradeId(),
                            _state.getStatExchangeWithdrawId(),
                            _state.getStatExchangeTradeState(),
                            _state.getEndExchangeTradeState(),
                            _state.getStatExchangeWithdrawState(),
                            newCoinBalance
                    );
                }
            }
        }

    }

    public static void updateTradeState() {

        switch(state().getStatExchange()) {
            case BINANCE -> {
                Map<String, OrderHistoryModel> orderHistory = BinanceRepository.orderHistory(state().getCurrentCoin());
                System.out.println(orderHistory);
                System.out.println("containsKeyRe "+ !orderHistory.containsKey(_state.getStatExchangeTradeId()));

                if (orderHistory.containsKey(_state.getStatExchangeTradeId())) {
                    _state = _state.copy(
                            _state.getCurrentCoin(),
                            _state.getEndCoin(),
                            _state.getTradeAmount(),
                            _state.getCurrentBlockchain(),
                            _state.getStatExchange(),
                            _state.getEndExchange(),
                            _state.getStatExchangeTradeId(),
                            _state.getEndExchangeTradeId(),
                            _state.getStatExchangeWithdrawId(),
                            orderHistory.get(_state.getStatExchangeTradeId()).getState(),
                            _state.getEndExchangeTradeState(),
                            _state.getStatExchangeWithdrawState(),
                            _state.getExchangeData()
                    );
                }
            }
        }

    }

    public static void updateWithdrawState() {

        switch(state().getStatExchange()) {
            case BINANCE -> {
                Map<String, WithdrawHistoryModel> orderHistory = BinanceRepository.withdrawHistory();
                System.out.println("orderHistory "+orderHistory);
                System.out.println("WithdrawId "+_state.getStatExchangeWithdrawId());

                if (orderHistory.containsKey(_state.getStatExchangeWithdrawId())) {
                    System.out.println("state "+orderHistory.get(_state.getStatExchangeWithdrawId()).getState());
                    _state = _state.copy(
                            _state.getCurrentCoin(),
                            _state.getEndCoin(),
                            _state.getTradeAmount(),
                            _state.getCurrentBlockchain(),
                            _state.getStatExchange(),
                            _state.getEndExchange(),
                            _state.getStatExchangeTradeId(),
                            _state.getEndExchangeTradeId(),
                            _state.getStatExchangeWithdrawId(),
                            _state.getStatExchangeTradeState(),
                            _state.getEndExchangeTradeState(),
                            orderHistory.get(_state.getStatExchangeWithdrawId()).getState(),
                            _state.getExchangeData()
                    );
                }
            }
        }

    }

    public static BigDecimal profitAfterFees(
            String symbol,
            String endSymbol
    ) {
        MainStateModel.ExchangeData startExchange = state().getExchangeData().get(state().getStatExchange());
        MainStateModel.ExchangeData endExchange = state().getExchangeData().get(state().getEndExchange());
        MainStateModel.ExchangeData.CoinData startCoin = startExchange.getCoinsData().get(symbol);
        MainStateModel.ExchangeData.CoinData endCoin = endExchange.getCoinsData().get(endSymbol);
        List<MainStateModel.ExchangeData.CoinData.Blockchain> startBlockChains = new ArrayList<>(startCoin.getBlockchainsAndFees().values());
        Map<String, MainStateModel.ExchangeData.CoinData.Blockchain> endBlockChains = endCoin.getBlockchainsAndFees();

        MainStateModel.ExchangeData.CoinData.Blockchain startBlockchain = null;
        MainStateModel.ExchangeData.CoinData.Blockchain endBlockchain = null;

        startBlockChains.sort(Comparator.comparing(MainStateModel.ExchangeData.CoinData.Blockchain::getFee));

        for (MainStateModel.ExchangeData.CoinData.Blockchain blockchain : startBlockChains) {
            if (endBlockChains.containsKey(blockchain.getBlockchain())) {
                MainStateModel.ExchangeData.CoinData.Blockchain endBlockChain = endBlockChains.get(blockchain.getBlockchain());
                if (!endBlockChain.getAddress().equals("null")) {
                    if (endBlockChain.getWithdrawTime() < 40) {
                        System.out.println("start"+ blockchain);
                        System.out.println("end"+ endBlockChain);
                        startBlockchain = blockchain;
                        endBlockchain = endBlockChain;
                        break;
                    }
                }
            }
        }
        BigDecimal usdtAmount = FindUsdtAmount.getUsdtAmount(state().getStatExchange());


        //usdtAvailable / CoinPrice
        //make start coin and end price to acctual coin qauntitiy
        BigDecimal isProfitable = new BigDecimal("0.0");

        if (endBlockchain != null && usdtAmount != null) {
            MainStateModel.ExchangeData.CoinData.Blockchain currentBlockchain = (startBlockchain.getFee().compareTo(endBlockchain.getFee()) > 0) ? startBlockchain : endBlockchain;

            isProfitable = AlbritageCalculation.calculateProfit(
                    usdtAmount,
                    startCoin,
                    endCoin,
                    currentBlockchain
            );

            _state = _state.copy(
                    _state.getCurrentCoin(),
                    _state.getEndCoin(),
                    _state.getTradeAmount(),
                    endBlockchain,
                    _state.getStatExchange(),
                    _state.getEndExchange(),
                    _state.getStatExchangeTradeId(),
                    _state.getEndExchangeTradeId(),
                    _state.getStatExchangeWithdrawId(),
                    _state.getStatExchangeTradeState(),
                    _state.getEndExchangeTradeState(),
                    _state.getStatExchangeWithdrawState(),
                    _state.getExchangeData()
            );
        }

        System.out.println(isProfitable);

        return isProfitable;
    }

    // coin qty according to price,
    public static BigDecimal getCoinQty(
            ExchangeEnums exchange,
            MainStateModel.ExchangeData.CoinData coin,
            BigDecimal price,
            OrderSide side
    ) {
        MainStateModel.ExchangeData.CoinData.OrderBook orderBook = state().getExchangeData().get(exchange).getCoinsData()
                .get(coin.getSymbol())
                .getOrderBook();


        BigDecimal output = new BigDecimal("0");

        myLog.info("price" + price.toString());
        myLog.info(orderBook.getAsks().toString());

        for (MainStateModel.ExchangeData.CoinData.OrderBook.OrderBookItem orderItem : (side == OrderSide.SELL) ? orderBook.getAsks() : orderBook.getBids()) {
            if ((side == OrderSide.BUY ) ? orderItem.getPrice().compareTo(price) >= 0 : orderItem.getPrice().compareTo(price) <= 0) {
                output = output.add(orderItem.getQty());
            }
        }

        myLog.info("qty current" + output.toString());

        return output;
    }

    public static void buyCoin() {
        MainStateModel.ExchangeData.CoinData currentCoin = state().getExchangeData()
                .get(state().getStatExchange())
                .getCoinsData()
                .get(state().getCurrentCoin());

        BigDecimal usdtAmount = state().getTradeAmount();

        switch (state().getStatExchange()) {
            case BINANCE -> {
                OrderModel tradeId = BinanceRepository.buyOrder(
                        state().getCurrentCoin(),
                        RoundToMinQty.roundToMinQty(
                                usdtAmount.subtract(new BigDecimal("1")),
                                state().getCurrentCoin(),
                                state().getStatExchange()
                        ).divide(currentCoin.getPrice(), RoundingMode.HALF_EVEN)
                );
                System.out.println("usdtAmount " + usdtAmount);
                System.out.println("RoundToMinQty Bi  " + RoundToMinQty.roundToMinQty(
                        usdtAmount,
                        state().getCurrentCoin(),
                        state().getStatExchange()
                ).divide(currentCoin.getPrice(), RoundingMode.HALF_EVEN));
                if (tradeId != null) {
                    _state = _state.copy(
                            _state.getCurrentCoin(),
                            _state.getEndCoin(),
                            _state.getTradeAmount(),
                            _state.getCurrentBlockchain(),
                            _state.getStatExchange(),
                            _state.getEndExchange(),
                            String.valueOf(tradeId.getId()),
                            _state.getEndExchangeTradeId(),
                            _state.getStatExchangeWithdrawId(),
                            _state.getStatExchangeTradeState(),
                            _state.getEndExchangeTradeState(),
                            _state.getStatExchangeWithdrawState(),
                            _state.getExchangeData()
                    );
                }
            }
            case LUNO -> {
                PostMarketOrderDto tradeId = LunoRepository.Buy(
                        state().getCurrentCoin(),
                        RoundToMinQty.roundToMinQty(
                                usdtAmount.subtract(new BigDecimal("1")),
                                state().getCurrentCoin(),
                                state().getStatExchange()
                        ).divide(currentCoin.getPrice(), RoundingMode.HALF_EVEN)
                );
                System.out.println("usdtAmount " + usdtAmount);
                System.out.println("RoundToMinQty Bi  " + RoundToMinQty.roundToMinQty(
                        usdtAmount,
                        state().getCurrentCoin(),
                        state().getStatExchange()
                ).divide(currentCoin.getPrice(), RoundingMode.HALF_EVEN));
                if (tradeId != null) {
                    _state = _state.copy(
                            _state.getCurrentCoin(),
                            _state.getEndCoin(),
                            _state.getTradeAmount(),
                            _state.getCurrentBlockchain(),
                            _state.getStatExchange(),
                            _state.getEndExchange(),
                            tradeId.getOrder_id(),
                            _state.getEndExchangeTradeId(),
                            _state.getStatExchangeWithdrawId(),
                            _state.getStatExchangeTradeState(),
                            _state.getEndExchangeTradeState(),
                            _state.getStatExchangeWithdrawState(),
                            _state.getExchangeData()
                    );
                }
            }
        }

    }
    public static void withdrawCoin() {

        System.out.println(state().getCurrentBlockchain());

        for(int i=0; i<100; i++){
            updateTradeState();
            System.out.println(state().getStatExchangeTradeState());

            if (state().getStatExchangeTradeState() == ExchangeStates.COMPLETED) {
                for(int ii=0; ii<100; ii++) {
                    updateCoinBalance(state().getCurrentCoin());
                    MainStateModel.ExchangeData.CoinData currentCoin = state().getExchangeData().get(state().getStatExchange()).getCoinsData().get(state().getCurrentCoin());
                    BigDecimal withdrawAmount = currentCoin.getBalance();
                    System.out.println("withdrawAmount "+withdrawAmount);
                    if (withdrawAmount.compareTo(
                            RoundToMinQty.roundToMinQty(
                                    new BigDecimal(5), state().getCurrentCoin(), state().getStatExchange()
                            ).divide(currentCoin.getPrice(), RoundingMode.HALF_EVEN)
                    ) > 0.0) {
                        switch(state().getStatExchange()) {
                            case BINANCE -> {
                                WithdrawModel withdrawId =  BinanceRepository.withdraw(
                                        state().getCurrentCoin().split("_")[0],
                                        state().getCurrentBlockchain().getAddress(),
                                        withdrawAmount,
                                        state().getCurrentBlockchain().getTag()
                                );
                                System.out.println(state().getExchangeData().get(state().getStatExchange()).getCoinsData().get(state().getCurrentCoin()));

                                if (withdrawId != null) {
                                    _state = _state.copy(
                                            _state.getCurrentCoin(),
                                            _state.getEndCoin(),
                                            _state.getTradeAmount(),
                                            _state.getCurrentBlockchain(),
                                            _state.getStatExchange(),
                                            _state.getEndExchange(),
                                            _state.getStatExchangeTradeId(),
                                            _state.getEndExchangeTradeId(),
                                            withdrawId.getId(),
                                            _state.getStatExchangeTradeState(),
                                            _state.getEndExchangeTradeState(),
                                            _state.getStatExchangeWithdrawState(),
                                            _state.getExchangeData()
                                    );
                                }
                            }

                        }
                        updateWithdrawState();
                        if (state().getStatExchangeWithdrawState() != ExchangeStates.FAILED) {
                            break;
                        }
                    }
                }
                break;
            }
            try {TimeUnit.SECONDS.sleep(30);} catch (InterruptedException e) {System.out.println(e);}
        }
    }
    public static void sellCoin() {
        for(int i=0; i<100; i++){
            updateWithdrawState();
            if (state().getStatExchangeWithdrawState() == ExchangeStates.COMPLETED) {
                for(int ii=0; ii<100; ii++) {
                    updateCoinBalance(state().getEndCoin());
                    MainStateModel.ExchangeData.CoinData currentCoin = state().getExchangeData().get(state().getEndExchange()).getCoinsData().get(state().getEndCoin());
                    BigDecimal withdrawAmount = currentCoin.getBalance();
                    if (state().getEndExchange() == ExchangeEnums.BINANCE) {
                        withdrawAmount = BinanceRepository.withdrawFee().get(currentCoin.getSymbol().split("_")[0]).getBalance();
                    } else {

                    }

                    System.out.println("withdrawAmount "+withdrawAmount);
                    if (withdrawAmount.compareTo(
                            RoundToMinQty.roundToMinQty(
                                    new BigDecimal(5), state().getEndCoin(), state().getEndExchange()
                            ).divide(currentCoin.getPrice(), RoundingMode.HALF_EVEN)
                    ) > 0.0) {

                        BigDecimal sellAmount = RoundToMinQty.roundToMinQty(
                                withdrawAmount,
                                state().getEndCoin(),
                                state().getEndExchange()
                        );

                        System.out.println("sellAmount "+sellAmount);
                        System.out.println("balance " + state().getExchangeData().get(state().getEndExchange()).getCoinsData().get(state().getCurrentCoin()).getBalance());
                        switch(state().getEndExchange()) {
                            case BINANCE -> {
                                OrderModel tradeId =  BinanceRepository.sellOrder(
                                        state().getEndCoin(),
                                        sellAmount.toString()
                                );
                                if (tradeId != null) {
                                    _state = _state.copy(
                                            _state.getCurrentCoin(),
                                            _state.getEndCoin(),
                                            _state.getTradeAmount(),
                                            _state.getCurrentBlockchain(),
                                            _state.getStatExchange(),
                                            _state.getEndExchange(),
                                            _state.getStatExchangeTradeId(),
                                            tradeId.getId(),
                                            _state.getStatExchangeWithdrawId(),
                                            _state.getStatExchangeTradeState(),
                                            _state.getEndExchangeTradeState(),
                                            _state.getStatExchangeWithdrawState(),
                                            _state.getExchangeData()
                                    );
                                }
                            }
                            case LUNO -> {
                                PostMarketOrderDto tradeId =  LunoRepository.Sell(
                                        state().getEndCoin(),
                                        sellAmount
                                );
                                if (tradeId != null) {
                                    _state = _state.copy(
                                            _state.getCurrentCoin(),
                                            _state.getEndCoin(),
                                            _state.getTradeAmount(),
                                            _state.getCurrentBlockchain(),
                                            _state.getStatExchange(),
                                            _state.getEndExchange(),
                                            _state.getStatExchangeTradeId(),
                                            tradeId.getOrder_id(),
                                            _state.getStatExchangeWithdrawId(),
                                            _state.getStatExchangeTradeState(),
                                            _state.getEndExchangeTradeState(),
                                            _state.getStatExchangeWithdrawState(),
                                            _state.getExchangeData()
                                    );
                                }
                            }
                        }

                        break;
                    }
                    try {TimeUnit.SECONDS.sleep(30);} catch (InterruptedException e) {System.out.println(e);}
                }
                break;
            }

            try {TimeUnit.SECONDS.sleep(30);} catch (InterruptedException e) {System.out.println(e);}
        }
    }
}
