package org.example.exchanges.binance.dto

data class DepositHistoryDto(
        val id: String,
        val amount: Double,
        val coin: String,
        val network: String,
        val status: Int,
        val address: String,
        val addressTag: String,
        val txId: String,
        val insertTime: Long,
        val transferType: Int,
        val confirmTimes: String,
        val unlockConfirm: Int,
        val walletType: Int
)
