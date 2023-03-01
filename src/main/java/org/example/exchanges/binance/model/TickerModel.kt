package org.example.exchanges.binance.model

import java.math.BigDecimal

data class TickerModel(
        val symbol: String,
        val volume: Double,
        val price: BigDecimal
)