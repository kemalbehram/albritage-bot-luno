package org.example.exchanges.binance.model

import java.math.BigDecimal


data class WithdrawFeeModel(
        val symbol: String,
        val balance: BigDecimal,
        val networkList: List<Network>
) {
    data class Network(
            val network: String,
            val fee: BigDecimal,
            val withdrawTime: Int,
    )
}
