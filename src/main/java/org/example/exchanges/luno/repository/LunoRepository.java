package org.example.exchanges.luno.repository;

import org.example.domain.enums.OrderSide;
import org.example.domain.models.MainStateModel;
import org.example.exchanges.luno.LunoApi;
import org.example.exchanges.luno.converter.*;
import org.example.exchanges.luno.dto.*;
import org.example.exchanges.luno.dto.getBalance.GetBalanceDto;
import org.example.exchanges.luno.dto.getMarketsInfo.GetMarketsInfoDto;
import org.example.exchanges.luno.model.*;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LunoRepository {
    public static TradeFeeModel TradeFee() {
        GetFeeInformationDto getFeeInformationDto = LunoApi.GetFeeInformation("ADAZAR");
        if (getFeeInformationDto != null) {
            return TradeFeeConverter.tradeFeeConverter(getFeeInformationDto);
        } else {
            return null;
        }
    }

    public static WithdrawFeeModel WithdrawFee(
            BigDecimal amount,
            String currency,
            String address
    ) {
        EstimateSendFeesDto estimateSendFeesDto = LunoApi.EstimateSendFees(amount,currency,address);
        if (estimateSendFeesDto != null) {
            return WithdrawFeeConverter.withdrawFeeConverter(estimateSendFeesDto);
        } else {
            return null;
        }
    }

    public static Map<String, CurrenciesInformationModel> CurrenciesInformation() {
        GetMarketsInfoDto getMarketsInfoDto = LunoApi.GetMarketsInfo();
        if (getMarketsInfoDto != null) {
            System.out.println(getMarketsInfoDto);
            return CurrenciesInformationConverter.currenciesInformationConverter(getMarketsInfoDto);
        } else {
            return Map.of();
        }
    }

    public static Map<String, TickerModel> Tickers() {
        TickersForAllCurrenciesDto tickersForAllCurrenciesDto = LunoApi.TickersForAllCurrencies();
        if (tickersForAllCurrenciesDto != null) {
            return TickersConverter.tickersConverter(tickersForAllCurrenciesDto);
        } else {
            return Map.of();
        }
    }

    public static TickerModel Ticker(String symbol) {
        TickersForAllCurrenciesDto tickersForAllCurrenciesDto = LunoApi.TickerForCurrency(symbol);
        System.out.println(tickersForAllCurrenciesDto);
        if (tickersForAllCurrenciesDto != null && !tickersForAllCurrenciesDto.getTickers().isEmpty()) {
            return TickersConverter.tickersConverter(tickersForAllCurrenciesDto).values().stream().toList().get(0);
        } else {
            return null;
        }
    }

    public static Map<String, BalanceModel> Balances() {
        GetBalanceDto getBalanceDto = LunoApi.GetBalance();
        if (getBalanceDto != null) {
            return BalancesConverter.balanceConverter(getBalanceDto);
        } else {
            return Map.of();
        }
    }

    public static MainStateModel.ExchangeData.CoinData.OrderBook OrderBook(String symbol) {
        GetFullOrderBookDto getFullOrderBookDto = LunoApi.GetFullOrderBook(symbol);
        if (getFullOrderBookDto != null) {
            return OrderBookConverter.orderBookConverter(getFullOrderBookDto);
        } else {
            return new MainStateModel.ExchangeData.CoinData.OrderBook(List.of(), List.of());
        }
    }

    public static AddressModel Address(String currency) {
        GetReceiveAddressDto getReceiveAddressDto = LunoApi.GetReceiveAddress(currency);
        if (getReceiveAddressDto != null) {
            return AddressConverter.addressConverter(getReceiveAddressDto);
        } else {
            return null;
        }
    }

    public static AddressModel CreateAddress(String currency) {
        GetReceiveAddressDto createReceiveAddressDto = LunoApi.CreateReceiveAddress(currency);
        if (createReceiveAddressDto != null) {
            return AddressConverter.addressConverter(createReceiveAddressDto);
        } else {
            return null;
        }
    }

    public static LinkedHashMap<String, OrderHistoryModel> Orders() {
        ListOrdersDto listOrdersDto = LunoApi.ListOrders();
        if (listOrdersDto != null && listOrdersDto.getOrders() != null) {
            return OrderHistoryConverter.orderHistoryConverter(listOrdersDto);
        } else {
            return null;
        }
    }

    public static LinkedHashMap<String, WithdrawHistoryModel> Withdrawals() {
        ListWithdrawalRequestsDto listWithdrawalRequestsDto = LunoApi.ListWithdrawalRequests();
        if (listWithdrawalRequestsDto != null) {
            return WithdrawHistoryConverter.withdrawHistoryConverter(listWithdrawalRequestsDto);
        } else {
            return null;
        }
    }

    public static SendDto Send(
            BigDecimal amount,
            String currency,
            String address
    ) {
        SendDto sendDto = LunoApi.Send(amount,currency,address);
        if (sendDto != null) {
            return sendDto;
        } else {
            return null;
        }
    }

    public static PostMarketOrderDto Buy(
            String symbol,
            BigDecimal amount
    ) {
        PostMarketOrderDto postMarketOrderDto = LunoApi.PostMarketOrder(symbol,OrderSide.BUY.name(),amount);
        if (postMarketOrderDto != null) {
            return postMarketOrderDto;
        } else {
            return null;
        }
    }

    public static PostMarketOrderDto Sell(
            String symbol,
            BigDecimal amount
    ) {
        PostMarketOrderDto postMarketOrderDto = LunoApi.PostMarketOrder(symbol,OrderSide.SELL.name(),amount);
        if (postMarketOrderDto != null) {
            return postMarketOrderDto;
        } else {
            return null;
        }
    }
}
