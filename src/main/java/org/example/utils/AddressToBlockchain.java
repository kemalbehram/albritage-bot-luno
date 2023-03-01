package org.example.utils;

import java.util.regex.Pattern;

public class AddressToBlockchain {
    public static String addressToBlockchain(String address) {

        if (regex("^(0x)[0-9A-Fa-f]{40,64}").matcher(address).matches()) {
            return "ETH";
        }
        if (regex("^[13][a-km-zA-HJ-NP-Z1-9]{25,34}$|^[(bc1q)|(bc1p)][0-9A-Za-z]{37,62}").matcher(address).matches()) {
            return "BTC";
        }
        if (regex("^(([0-9A-Za-z]{57,59})|([0-9A-Za-z]{100,104}))").matcher(address).matches()) {
            return "ADA";
        }
        if (regex("^[1-9A-HJ-NP-Za-km-z]{25,60}").matcher(address).matches()) {
            return "XRP";
        }
        if (regex("").matcher(address).matches()) {
            //^(bnb1)[0-9a-z]{38}
            //bnb
            return "";
        }
        if (regex("").matcher(address).matches()) {
            //^(0x)[0-9A-Fa-f]{40}$ : BSC
            return "";
        }
        if (regex("").matcher(address).matches()) {
            //^[48][a-zA-Z|\d]{94}([a-zA-Z|\d]{11})?$ : XMR
            return "";
        }
        if (regex("").matcher(address).matches()) {
            //^(0x)[0-9A-Fa-f]{40}$ : MOVR
            return "";
        }
        if (regex("").matcher(address).matches()) {
            //^(0x)[0-9A-Fa-f]{40}$ : CHZ
            return "";
        }
        if (regex("").matcher(address).matches()) {
            //^(FIO)[a-z-A-Z0-9]{50}$ : FIO
            return "";
        }
        if (regex("").matcher(address).matches()) {
            //^[a-z0-9]{41}$|[a-z0-9]{86}$ : FIL
            return "";
        }
        if (regex("").matcher(address).matches()) {
            //^[0-9a-z-A-Z]{44,50}$ : KSM
            return "";
        }
        if (regex("").matcher(address).matches()) {
            //^[Q|M][A-Za-z0-9]{33}$ : QTUM
            return "";
        }
        if (regex("").matcher(address).matches()) {
            //^G[A-D]{1}[A-Z2-7]{54}$ : XLM
            return "";
        }
        if (regex("").matcher(address).matches()) {
            //^(0x)[0-9A-Fa-f]{40}$ : ARBITRUM
            return "";
        }
        if (regex("").matcher(address).matches()) {
            //^(3J)[0-9A-Za-z]{33}$ : LTO
            return "";
        }
        if (regex("").matcher(address).matches()) {
            //^[0-9a-z-A-Z]{44,50}$ : PARA
            return "";
        }
        if (regex("").matcher(address).matches()) {
            //^T[1-9A-HJ-NP-Za-km-z]{33}$ : TRX
            return "";
        }
        if (regex("").matcher(address).matches()) {
            //^(L|M)[A-Za-z0-9]{33}$|^(ltc1)[0-9A-Za-z]{39}$ : LTC
            return "";
        }
        if (regex("").matcher(address).matches()) {
            //^[1-9A-HJ-NP-Za-km-z]{32,44}$ : SOL
            return "";
        }
        if (regex("").matcher(address).matches()) {
            //^[1-5a-z\.]{1,12}$ : EOS
            return "";
        }
        if (regex("").matcher(address).matches()) {
            //^(X-avax)[0-9A-za-z]{39}$ : AVAX
            return "";
        }
        if (regex("").matcher(address).matches()) {
            //^(0x)[0-9A-Fa-f]{40}$ : AVAXC
            return "";
        }
        return null;
    }

    private static Pattern regex(String pattern) {
        return Pattern.compile(pattern);
    }
}
