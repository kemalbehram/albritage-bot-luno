package org.example.exchanges.binance.dto

data class WithdrawHistoryDto(
        val id: String,
        val amount: Double,
        val transactionFee: Double,
        val coin: String,
        val status: Int,
        val address: String,
        val txId: String,
        val applyTime: String,
        val network: String,
        val transferType: Int,
        val withdrawOrderId: String,
        val info: String,
        val confirmNo: Int,
        val walletType: Int,
        val txKey: String
)
