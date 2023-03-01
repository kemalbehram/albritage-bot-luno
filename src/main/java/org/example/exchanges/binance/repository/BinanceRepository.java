package org.example.exchanges.binance.repository;

import org.example.domain.enums.OrderSide;
import org.example.domain.enums.OrderTypes;
import org.example.domain.models.MainStateModel;
import org.example.exchanges.binance.BinanceApi;
import org.example.exchanges.binance.converter.*;
import org.example.exchanges.binance.dto.*;
import org.example.exchanges.binance.model.*;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BinanceRepository {
    //trade fee
    public static TradeFeeModel tradeFee() {
        AccountInformationDto accountInformationDto = BinanceApi.accountInformation();
        if (accountInformationDto != null) {
            return TradeFeeConverter.TradeFeeConverter(accountInformationDto);
        } else {
            return null;
        }
    }
    //address, shall be saved
    public static AddressModel address(
            String coin,
            String network
    ) {
        DepositAddressRequestDto request = new DepositAddressRequestDto(coin, network);
        DepositAddressResponseDto depositAddressResponseDto = BinanceApi.address(request);
        if (depositAddressResponseDto != null) {
            return AddressConverter.addressConverter(depositAddressResponseDto);
        } else {
            return null;
        }
    }
    //order state
    public static Map<String, OrderHistoryModel> orderHistory(
            String symbol
    ) {
        AllOrdersRequestDto request = new AllOrdersRequestDto(symbol.replace("_", ""));
        List<AllOrdersResponseDto> allOrdersResponseDtoList = BinanceApi.orderHistory(request);
        System.out.println(allOrdersResponseDtoList);
        return OrderHistoryRRConverter.orderHistoryConverter(allOrdersResponseDtoList);
    }
    //withdraw state
    public static Map<String, WithdrawHistoryModel> withdrawHistory() {
        List<WithdrawHistoryDto> withdrawHistoryDtoList = BinanceApi.withdrawHistory();
        System.out.println(withdrawHistoryDtoList);
        return WithdrawHistoryConverter.withdrawHistoryConverter(withdrawHistoryDtoList);
    }

    //unused
    public static Map<String, DepositHistoryModel> depositHistory() {
        List<DepositHistoryDto> depositHistoryDtoList = BinanceApi.depositHistory();
        return DepositHistoryConverter.depositHistoryConverter(depositHistoryDtoList);
    }
    //withdraw id
    public static WithdrawModel withdraw(
            String coin,
            String address,
            BigDecimal amount,
            String tag
    ) {
        Map request = Map.of();
        if (tag.isBlank()) {
            request = Map.of(
                    "coin", coin,
                    "address", address,
                    "amount", amount.toString()
            );
        } else {
            request = Map.of(
                    "coin", coin,
                    "address", address,
                    "amount", amount.toString(),
                    "addressTag", tag
            );
        }
        WithdrawResponseDto withdrawResponseDto = BinanceApi.withdraw(new LinkedHashMap<>(request));
        System.out.println(withdrawResponseDto);
        if (withdrawResponseDto != null) {
            return WithdrawConverter.withdrawConverter(withdrawResponseDto);
        } else {
            return null;
        }
    }
    //order id
    public static OrderModel buyOrder(
            String symbol,
            BigDecimal amount
    ) {
        NewOrderResponseDto NewOrderResponseDto = BinanceApi.trade(
                new NewOrderRequestDto(
                        symbol.replace("_", ""),
                        OrderSide.BUY.name(),
                        OrderTypes.MARKET.name(),
                        amount.toString()
                )
        );
        System.out.println(NewOrderResponseDto);
        if (NewOrderResponseDto != null) {
            return OrderConverter.orderConverter(NewOrderResponseDto);
        } else {
            return null;
        }
    }
    //trade id
    public static OrderModel sellOrder(
            String symbol,
            String amount
    ) {
        NewOrderResponseDto NewOrderResponseDto = BinanceApi.trade(
                new NewOrderRequestDto(
                        symbol.replace("_",""),
                        OrderSide.SELL.name(),
                        OrderTypes.MARKET.name(),
                        amount
                )
        );
        System.out.println(NewOrderResponseDto);
        if (NewOrderResponseDto != null) {
            return OrderConverter.orderConverter(NewOrderResponseDto);
        } else {
            return null;
        }
    }
    //withdraw fee
    //blockchains
    //coinWithdrawTime
    //balances
    public static Map<String, WithdrawFeeModel> withdrawFee() {
        List<AllCoinsInformationDto> NewOrderResponseDto = BinanceApi.allCoinsInformation();
        return WithdrawFeeConverter.withdrawFeeConverter(NewOrderResponseDto);
    }

    //coinPrices
    //dailyCoinChanges
    public static Map<String, TickerModel> tickers() {
        List<Ticker24hResponseDto> ticker24hDto = BinanceApi.tickers24h();
        return TickerConverter.tickerConverter(ticker24hDto);
    }

    //coinPrice
    //dailyCoinChange
    public static TickerModel ticker(String symbol) {
        Ticker24hResponseDto ticker24hDto = BinanceApi.ticker24h(new Ticker24hRequestDto(symbol.replace("_","")));
        if (ticker24hDto != null) {
            return TickerConverter.tickerConverter(List.of(ticker24hDto)).values().stream().toList().get(0);
        } else {
            return null;
        }
    }

    //all coin names
    //lot size
    public static Map<String, CoinInformationModel> coinInformation() {
        ExchangeInformationDto exchangeInformationDto = BinanceApi.exchangeInformation();

        if (exchangeInformationDto != null) {
            return CoinInformationConverter.coinInformationConverter(exchangeInformationDto.getSymbols());
        } else  {
            return Map.of();
        }
    }

    public static MainStateModel.ExchangeData.CoinData.OrderBook orderBook(String symbol) {
        OrderBookResponseDto response = BinanceApi.orderBook(new OrderBookRequestDto(symbol.replace("_",""), 100));
        //System.out.println(response);
        if (response != null) {
            return OrderBookConverter.orderBookConverter(response);
        } else  {
            return null;
        }
    }
}
