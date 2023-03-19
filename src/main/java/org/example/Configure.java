package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Configure {

    public static Double SLIPPAGE_PROCENT = 2.0;
    public static List<String> BlockchainNetworks = new ArrayList<String>(List.of(
            "ETH",
            "BNB",
            "TRX"
    ));
    //"BTT",
    public static List<String> TRC20 = new ArrayList<String>(List.of(
            "BTC",
            "LTC",
            "ETH",
            "ETC",
            "ZEC",
            //"DOGE",
            "TRX",
            "WIN",
            "XTZ",
            "AVA",
            "BNB",
            "BUSD",
            "WRX",
            "DOT",
            "BCH",
            "TUSD",
            //"BTCST",
            "FIL",
            "CAKE",
            "XVS",
            "FTM",
            //"BURGER",
            "ADA",
            "JST",
            "SUN"
    ));

    public static Map<String, Map<String, String>> POLONIEX_DEPOSIT_ADDRESSES = Map.ofEntries(
            Map.entry("BTC",Map.of("address", "TMqrdV6vH8ccUXsGnf2B378J3LwEsCqKy9","addressTag", "")),
            Map.entry("LTC",Map.of("address", "TMqrdV6vH8ccUXsGnf2B378J3LwEsCqKy9","addressTag", "")),
            Map.entry("ETH",Map.of("address", "TMqrdV6vH8ccUXsGnf2B378J3LwEsCqKy9","addressTag", "")),
            Map.entry("ETC",Map.of("address", "0x20b7240ae0fbd92ff5f09da90ec6b8f4ae6da6db","addressTag", "")),
            Map.entry("ZEC",Map.of("address", "t1dLEBa8zmuhT46Zu9Uq82Cxn2SPm4CESx4","addressTag", "")),
            Map.entry("DOGE",Map.of("address", "TMqrdV6vH8ccUXsGnf2B378J3LwEsCqKy9","addressTag", "")),
            Map.entry("TRX",Map.of("address", "TJLH195nuGNvbyuG2Tt5hBog2fDzjSuiQp","addressTag", "")),
            Map.entry("WIN",Map.of("address", "TMqrdV6vH8ccUXsGnf2B378J3LwEsCqKy9","addressTag", "")),
            Map.entry("XTZ",Map.of("address", "tz1gA7chx3n2wHKn6NsNnGkkDvHesPt9F8dM","addressTag", "")),
            Map.entry("AVA",Map.of("address", "bnb1jscshkv3uq3zrk37dh652mceqpns0sgymx2sy3","addressTag", "")),
            Map.entry("BNB",Map.of("address", "0xe8cc4284cc8a97ff85c1e079a0fda573d4a779b4","addressTag", "")),
            Map.entry("BUSD",Map.of("address", "0xe8cc4284cc8a97ff85c1e079a0fda573d4a779b4","addressTag", "")),
            Map.entry("WRX",Map.of("address", "bnb1jscshkv3uq3zrk37dh652mceqpns0sgymx2sy3","addressTag", "")),
            Map.entry("DOT",Map.of("address", "1gA8rWXjwsTzPmmW9uvvzQSBuke8X1fouY7mCeif6MzPoCk","addressTag", "")),
            Map.entry("BCH",Map.of("address", "1CGsRJ3eLWayZCLpLBLpcTctS33Hj7vjdw","addressTag", "")),
            Map.entry("TUSD",Map.of("address", "TMqrdV6vH8ccUXsGnf2B378J3LwEsCqKy9","addressTag", "")),
            Map.entry("BTCST",Map.of("address",  "TMqrdV6vH8ccUXsGnf2B378J3LwEsCqKy9","addressTag", "")),
            Map.entry("FIL",Map.of("address", "f1gwlhi32frxac5mdvjulomd2tc5ardvrs7mgpyzy","addressTag", "")),
            Map.entry("CAKE",Map.of("address", "0xe8cc4284cc8a97ff85c1e079a0fda573d4a779b4","addressTag", "")),
            Map.entry("XVS",Map.of("address", "0xe8cc4284cc8a97ff85c1e079a0fda573d4a779b4","addressTag", "")),
            Map.entry("FTM",Map.of("address", "0xf33103aad06b927f840035ba10e70bce5c986e29","addressTag", "")),
            Map.entry("BTT",Map.of("address", "TMqrdV6vH8ccUXsGnf2B378J3LwEsCqKy9","addressTag", "")),
            Map.entry("JST",Map.of("address", "TMqrdV6vH8ccUXsGnf2B378J3LwEsCqKy9","addressTag", "")),
            Map.entry("SUN",Map.of("address", "TMqrdV6vH8ccUXsGnf2B378J3LwEsCqKy9","addressTag", "")),
            Map.entry("USDT",Map.of("address", "TMqrdV6vH8ccUXsGnf2B378J3LwEsCqKy9","addressTag", "")),
            Map.entry("ADA",Map.of("address", "addr1v8dy37ew9tlr6enn0hq62ct2qn3hz5lpskm400sfy9sx2tc4m4s7g","tag", "")),
            Map.entry("BURGER", Map.of("address", "0xe8cc4284cc8a97ff85c1e079a0fda573d4a779b4","addressTag", "")),
            Map.entry("EOS", Map.of("address", "poloniexeos1","addressTag", "EPZLSPdjxA")),
            Map.entry("XRP", Map.of("address", "rwU8rAiE2eyEPz3sikfbHuqCuiAtdXqa2v","addressTag", "1995855895"))
    );
    //        Map.entry("BTC", "TMqrdV6vH8ccUXsGnf2B378J3LwEsCqKy9"
    //        Map.entry("LTC", "TMqrdV6vH8ccUXsGnf2B378J3LwEsCqKy9"
    //        Map.entry("ETH", "TMqrdV6vH8ccUXsGnf2B378J3LwEsCqKy9"
    //        Map.entry("ETC", "0x20b7240ae0fbd92ff5f09da90ec6b8f4ae6da6db"
    //        Map.entry("ZEC", "t1dLEBa8zmuhT46Zu9Uq82Cxn2SPm4CESx4"
    //        Map.entry("DOGE", "TMqrdV6vH8ccUXsGnf2B378J3LwEsCqKy9"
    //        Map.entry("TRX", "TJLH195nuGNvbyuG2Tt5hBog2fDzjSuiQp"
    //        Map.entry("WIN", "TMqrdV6vH8ccUXsGnf2B378J3LwEsCqKy9"
    //        Map.entry("XTZ", "tz1gA7chx3n2wHKn6NsNnGkkDvHesPt9F8dM"
    //        Map.entry("AVA", "bnb1jscshkv3uq3zrk37dh652mceqpns0sgymx2sy3"
    //        Map.entry("BNB", "0xe8cc4284cc8a97ff85c1e079a0fda573d4a779b4"
    //        Map.entry("BUSD", "0xe8cc4284cc8a97ff85c1e079a0fda573d4a779b4"
    //        Map.entry("WRX", "bnb1jscshkv3uq3zrk37dh652mceqpns0sgymx2sy3"
    //        Map.entry("DOT", "1gA8rWXjwsTzPmmW9uvvzQSBuke8X1fouY7mCeif6MzPoCk"
    //        Map.entry("BCH", "1CGsRJ3eLWayZCLpLBLpcTctS33Hj7vjdw"
    //        Map.entry("TUSD", "TMqrdV6vH8ccUXsGnf2B378J3LwEsCqKy9"
    //        Map.entry("BTCST",  "TMqrdV6vH8ccUXsGnf2B378J3LwEsCqKy9"
    //        Map.entry("FIL", "f1gwlhi32frxac5mdvjulomd2tc5ardvrs7mgpyzy"
    //        Map.entry("CAKE", "0xe8cc4284cc8a97ff85c1e079a0fda573d4a779b4"
    //        Map.entry("XVS", "0xe8cc4284cc8a97ff85c1e079a0fda573d4a779b4"
    //        Map.entry("FTM", "0xf33103aad06b927f840035ba10e70bce5c986e29"
    //        Map.entry("BTT", "TMqrdV6vH8ccUXsGnf2B378J3LwEsCqKy9"
    //        Map.entry("JST", "TMqrdV6vH8ccUXsGnf2B378J3LwEsCqKy9"
    //        Map.entry("SUN", "TMqrdV6vH8ccUXsGnf2B378J3LwEsCqKy9"
    //        Map.entry("USDT", "TMqrdV6vH8ccUXsGnf2B378J3LwEsCqKy9"
    //        Map.entry("ADA", "addr1v8dy37ew9tlr6enn0hq62ct2qn3hz5lpskm400sfy9sx2tc4m4s7g"
    //        Map.entry("BURGE "0xe8cc4284cc8a97ff85c1e079a0fda573d4a779b4"



    //too complicated
    //"EOS", poloniexeos1 EPZLSPdjxA
    //"XRP",  rwU8rAiE2eyEPz3sikfbHuqCuiAtdXqa2v 1995855895

    //"USDT", "TMqrdV6vH8ccUXsGnf2B378J3LwEsCqKy9",
    //        "BTT", "TMqrdV6vH8ccUXsGnf2B378J3LwEsCqKy9",
    //        "TRX", "TJLH195nuGNvbyuG2Tt5hBog2fDzjSuiQp",
    //        "TUSD", "TMqrdV6vH8ccUXsGnf2B378J3LwEsCqKy9",
    //        "WIN", "TMqrdV6vH8ccUXsGnf2B378J3LwEsCqKy9",
    //        "JST", "TMqrdV6vH8ccUXsGnf2B378J3LwEsCqKy9",
    //        "SUN", "TMqrdV6vH8ccUXsGnf2B378J3LwEsCqKy9"

}
