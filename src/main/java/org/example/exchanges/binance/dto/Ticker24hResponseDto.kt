package org.example.exchanges.binance.dto

import java.math.BigDecimal

data class Ticker24hResponseDto(
        val symbol: String,
        val priceChange: Double,
        val priceChangePercent: Double,
        val weightedAvgPrice: Double,
        val prevClosePrice: Double,
        val lastPrice: BigDecimal,
        val lastQty: Double,
        val bidQty: Double,
        val askPrice: Double,
        val askQty: Double,
        val openPrice: Double,
        val highPrice: Double,
        val lowPrice: Double,
        val volume: Double,
        val quoteVolume: Double,
        val openTime: Long,
        val closeTime: Long,
        val firstId: Long,
        val lastId: Long,
        val count: Long
)
