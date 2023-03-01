package org.example.exchanges.binance.dto

import org.apache.logging.log4j.core.util.Integers

data class OrderBookRequestDto(
        val symbol: String,
        val limit: Int
)
