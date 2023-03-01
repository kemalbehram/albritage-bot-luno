package org.example.exchanges.binance.dto

import java.math.BigDecimal

data class AllCoinsInformationDto(
        val coin: String,
        val depositAllEnable: Boolean,
        val free: BigDecimal,
        val freeze: Double,
        val ipoable: Double,
        val ipoing: Double,
        val isLegalMoney: Boolean,
        val locked: Double,
        val name: String,
        val networkList: List<Network>
) {
        data class Network(
                val addressRegex: String,
                val coin: String,
                val depositDesc: String,
                val depositEnable: Boolean,
                val isDefault: Boolean,
                val memoRegex: String,
                val minConfirm: Int,
                val name: String,
                val network: String,
                val resetAddressStatus: Boolean,
                val specialTips: String,
                val unLockConfirm: Int,
                val withdrawDesc: String,
                val withdrawEnable: Boolean,
                val withdrawFee: BigDecimal,
                val withdrawIntegerMultiple: Double,
                val withdrawMax: String,
                val withdrawMin: String,
                val sameAddress: Boolean,
                val estimatedArrivalTime: Int,
                val busy: Boolean
        )
}
