package org.example.exchanges.binance.dto

data class DepositAddressResponseDto(
        val address: String,
        val coin: String,
        val tag: String,
        val url: String
)