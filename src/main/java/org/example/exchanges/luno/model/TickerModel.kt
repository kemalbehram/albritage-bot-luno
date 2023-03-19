package org.example.exchanges.luno.model

import java.math.BigDecimal

data public class TickerModel(
        val symbol: String,
        val volume: Double,
        val price: BigDecimal
)