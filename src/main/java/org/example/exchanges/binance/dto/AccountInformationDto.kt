package org.example.exchanges.binance.dto

data class AccountInformationDto(
        val makerCommission: Double,
        val takerCommission: Double,
        val buyerCommission: Double,
        val sellerCommission: Double,
        val commissionRates: CommissionRates,
        val canTrade: Boolean,
        val canWithdraw: Boolean,
        val canDeposit: Boolean,
        val brokered: Boolean,
        val requireSelfTradePrevention: Boolean,
        val updateTime: Long,
        val accountType: String,
        val balances: List<Balance>,
        val permission: List<String>
) {
    data class CommissionRates(
            val maker: Double,
            val taker: Double,
            val buyer: Double,
            val seller: Double
    )

    data class Balance(
            val asset: String,
            val free: Double,
            val locked: Double
    )
}
