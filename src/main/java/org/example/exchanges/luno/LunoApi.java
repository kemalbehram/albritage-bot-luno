package org.example.exchanges.luno;

import com.google.gson.Gson;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.example.domain.enums.OrderSide;
import org.example.exchanges.luno.dto.*;

import java.math.BigDecimal;

import static org.example.di.ok_http_client.OkHttpClient.okHttpClient;

public class LunoApi {
    static String BASE_URL = "https://api.luno.com/api";
    static Request.Builder auth = new Request.Builder().addHeader("Authorization","Basic cWFrcTducGI2bWNnOmkwb2pQcWNwRXF5NXVXRkplVUYxREV5aTJ2b2J3R2dGajd5dHk2aW5IVk0=");

    //fees
    public static GetFeeInformationDto GetFeeInformation(String Pair) {
        try {
            Response response = okHttpClient.newCall(
                    auth.url(BASE_URL+"/1/fee_info?pair="+Pair)
                            .build()
            ).execute();
            String responseBody = response.body().string();
            if (response.code() <= 201) {
                return new Gson().fromJson(responseBody, GetFeeInformationDto.class);
            }
            System.out.println(responseBody);
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static EstimateSendFeesDto EstimateSendFees(
            BigDecimal amount,
            String currency,
            String address
    ) {
        try {
            Response response = okHttpClient.newCall(
                    auth.url(BASE_URL+"/1/send_fee?amount="+amount+"&currency="+currency+"&address="+address)
                            .build()
            ).execute();
            String responseBody = response.body().string();
            if (response.code() <= 201) {
                return new Gson().fromJson(responseBody, EstimateSendFeesDto.class);
            }
            System.out.println(responseBody);
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    //exchange data
    public static GetMarketsInfoDto GetMarketsInfo() {
        try {
            Response response = okHttpClient.newCall(
                    auth.url(BASE_URL+"/exchange/1/markets")
                            .build()
            ).execute();
            String responseBody = response.body().string();
            if (response.code() <= 201) {
                return new Gson().fromJson(responseBody, GetMarketsInfoDto.class);
            }
            System.out.println(responseBody);
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static TickersForAllCurrenciesDto TickersForAllCurrencies() {
        try {
            Response response = okHttpClient.newCall(
                    auth.url(BASE_URL+"/1/tickers")
                            .build()
            ).execute();
            String responseBody = response.body().string();
            if (response.code() <= 201) {
                return new Gson().fromJson(responseBody, TickersForAllCurrenciesDto.class);
            }
            System.out.println(responseBody);
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static TickersForAllCurrenciesDto TickerForCurrency(String symbol) {
        try {
            Response response = okHttpClient.newCall(
                    auth.url(BASE_URL+"/1/tickers?pair="+symbol)
                            .build()
            ).execute();
            String responseBody = response.body().string();
            if (response.code() <= 201) {
                return new Gson().fromJson(responseBody, TickersForAllCurrenciesDto.class);
            }
            System.out.println(responseBody);
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static GetBalanceDto GetBalance() {
        try {
            Response response = okHttpClient.newCall(
                    auth.url(BASE_URL+"/1/balance")
                            .build()
            ).execute();
            String responseBody = response.body().string();
            if (response.code() <= 201) {
                return new Gson().fromJson(responseBody, GetBalanceDto.class);
            }
            System.out.println(responseBody);
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static GetFullOrderBookDto GetFullOrderBook(String symbol) {
        try {
            Response response = okHttpClient.newCall(
                    auth.url(BASE_URL+"/1/orderbook?pair="+symbol)
                            .build()
            ).execute();
            String responseBody = response.body().string();
            if (response.code() <= 201) {
                return new Gson().fromJson(responseBody, GetFullOrderBookDto.class);
            }
            System.out.println(responseBody);
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static GetReceiveAddressDto GetReceiveAddress(
            String asset
    ) {
        try {
            Response response = okHttpClient.newCall(
                    auth.url(BASE_URL+"/1/funding_address?asset="+asset)
                            .build()
            ).execute();
            String responseBody = response.body().string();
            if (response.code() <= 201) {
                return new Gson().fromJson(responseBody, GetReceiveAddressDto.class);
            }
            System.out.println(responseBody);
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static GetReceiveAddressDto CreateReceiveAddress(
            String asset
    ) {
        try {
            Response response = okHttpClient.newCall(
                    auth.url(BASE_URL+"/1/funding_address?asset="+asset)
                            .build()
            ).execute();
            String responseBody = response.body().string();
            if (response.code() <= 201) {
                return new Gson().fromJson(responseBody, GetReceiveAddressDto.class);
            }
            System.out.println(responseBody);
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static ListOrdersDto ListOrders() {
        try {
            Response response = okHttpClient.newCall(
                    auth.url(BASE_URL+"/1/listorders")
                            .build()
            ).execute();
            String responseBody = response.body().string();
            if (response.code() <= 201) {
                return new Gson().fromJson(responseBody, ListOrdersDto.class);
            }
            System.out.println(responseBody);
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static ListWithdrawalRequestsDto ListWithdrawalRequests() {
        try {
            Response response = okHttpClient.newCall(
                    auth.url(BASE_URL+"/1/withdrawals")
                            .build()
            ).execute();
            String responseBody = response.body().string();
            if (response.code() <= 201) {
                return new Gson().fromJson(responseBody, ListWithdrawalRequestsDto.class);
            }
            System.out.println(responseBody);
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    //buy, sell ,withdraw
    public static SendDto Send(
            BigDecimal amount,
            String currency,
            String address
    ) {
        try {
            Response response = okHttpClient.newCall(
                    auth.url(BASE_URL+"/1/send?amount="+amount+"&currency="+currency+"&address="+address)
                            .post(RequestBody.create(MediaType.parse("application/json"), ""))
                            .build()
            ).execute();
            String responseBody = response.body().string();
            if (response.code() <= 201) {
                return new Gson().fromJson(responseBody, SendDto.class);
            }
            System.out.println(responseBody);
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static PostMarketOrderDto PostMarketOrder(
            String Pair,
            String side,
            BigDecimal amount
    ) {
        try {
            Response response = okHttpClient.newCall(
                    auth.url(BASE_URL+"/1/marketorder?pair="+Pair+"&type="+side+((side.equals(OrderSide.BUY.name())) ? "&counter_volume=" : "&base_volume=") + amount)
                            .post(RequestBody.create(MediaType.parse("application/json"), ""))
                            .build()
            ).execute();
            String responseBody = response.body().string();
            if (response.code() <= 201) {
                return new Gson().fromJson(responseBody, PostMarketOrderDto.class);
            }
            System.out.println(responseBody);
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
