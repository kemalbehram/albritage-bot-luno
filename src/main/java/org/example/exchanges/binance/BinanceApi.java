package org.example.exchanges.binance;

import com.binance.connector.client.impl.SpotClientImpl;
import com.google.gson.Gson;
import okhttp3.Request;
import okhttp3.Response;
import org.example.Configure;
import org.example.exchanges.binance.dto.*;


import java.util.LinkedHashMap;
import java.util.List;

import static org.example.di.Logger.ResultLogger.myLog;
import static org.example.di.ok_http_client.OkHttpClient.okHttpClient;


public class BinanceApi {
    private static String host = "https://api.binance.com";
    private static final SpotClientImpl client = new SpotClientImpl(Configure.BI_API_KEY, Configure.BI_SECRET_KEY);

    public static AccountInformationDto accountInformation() {
        try {
            return new Gson().fromJson(client.createTrade().account(new LinkedHashMap<String,Object>()), AccountInformationDto.class);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    public static List<AllOrdersResponseDto> orderHistory(AllOrdersRequestDto parameters) {
        try {
            Gson gson = new Gson();
            LinkedHashMap request = gson.fromJson(gson.toJson(parameters), LinkedHashMap.class);
            return List.of(gson.fromJson(client.createTrade().getOrders(request), AllOrdersResponseDto[].class));
        } catch (Exception e) {
            System.out.println(e);
            return List.of();
        }
    }

    public static List<WithdrawHistoryDto> withdrawHistory() {
        try {
            System.out.println(client.createWallet().withdrawHistory(new LinkedHashMap()));
            return List.of(new Gson().fromJson(client.createWallet().withdrawHistory(new LinkedHashMap()), WithdrawHistoryDto[].class));
        } catch (Exception e) {
            System.out.println(e);
            return List.of();
        }
    }
    public static List<DepositHistoryDto>  depositHistory() {
        try {
            return List.of(new Gson().fromJson(client.createWallet().depositHistory(new LinkedHashMap<String,Object>()), DepositHistoryDto.class));
        } catch (Exception e) {
            System.out.println(e);
            return List.of();
        }
    }
    public static WithdrawResponseDto withdraw(LinkedHashMap parameters) {
        try {
            Gson gson = new Gson();
            String response = client.createWallet().withdraw(parameters);

            myLog.info(parameters+"");
            myLog.info(response);
            return gson.fromJson(response, WithdrawResponseDto.class);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    public static DepositAddressResponseDto address(DepositAddressRequestDto parameters) {
        try {
            Gson gson = new Gson();
            LinkedHashMap request = gson.fromJson(gson.toJson(parameters), LinkedHashMap.class);
            return gson.fromJson(client.createWallet().depositAddress(request), DepositAddressResponseDto.class);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    public static NewOrderResponseDto trade(NewOrderRequestDto parameters) {
        try {
            Gson gson = new Gson();
            LinkedHashMap request = gson.fromJson(gson.toJson(parameters), LinkedHashMap.class);

            String response = client.createTrade().newOrder(request);
            myLog.info(request+"");
            myLog.info(response);
            return gson.fromJson(response, NewOrderResponseDto.class);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static List<AllCoinsInformationDto> allCoinsInformation() {
            return List.of(new Gson().fromJson(client.createWallet().coinInfo(new LinkedHashMap<>()), AllCoinsInformationDto[].class));

    }

    public static String test() {
        LinkedHashMap<String, Object> ss = new LinkedHashMap<>();
        return client.createMarket().bookTicker(ss);
    }
    public static List<Ticker24hResponseDto> tickers24h() {
        try {
            Response response = okHttpClient.newCall(new Request.Builder()
                    .url(host+"/api/v3/ticker/24hr")
                    .build()
            ).execute();
            return List.of(new Gson().fromJson(response.body().string(), Ticker24hResponseDto[].class));
        } catch (Exception e) {
            System.out.println(e);
            return List.of();
        }
    }

    public static Ticker24hResponseDto ticker24h(Ticker24hRequestDto requestDto) {
        try {
            Response response = okHttpClient.newCall(new Request.Builder()
                    .url(host+"/api/v3/ticker/24hr?symbol="+requestDto.getSymbol())
                    .build()
            ).execute();
            String responseString = response.body().string();

            if (response.code() == 200) {
                return new Gson().fromJson(responseString, Ticker24hResponseDto.class);
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static ExchangeInformationDto exchangeInformation() {
        try {
            Response response = okHttpClient.newCall(new Request.Builder()
                    .url(host+"/api/v3/exchangeInfo")
                    .build()
            ).execute();
            return new Gson().fromJson(response.body().string(), ExchangeInformationDto.class);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static OrderBookResponseDto orderBook(OrderBookRequestDto requestDto) {
        try {
            Response response = okHttpClient.newCall(new Request.Builder()
                    .url(host+"/api/v3/depth?symbol="+requestDto.getSymbol()+"&limit="+requestDto.getLimit())
                    .build()
            ).execute();
            return new Gson().fromJson(response.body().string(), OrderBookResponseDto.class);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
