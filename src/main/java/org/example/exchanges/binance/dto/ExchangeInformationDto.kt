package org.example.exchanges.binance.dto

data class ExchangeInformationDto(
        val timezone: String,
        val serverTime: Long,
        val symbols: List<Symbol>
) {
    data class Symbol(
            val symbol: String,
            val status: String,
            val baseAsset: String,
            val baseAssetPrecision: Int,
            val quoteAsset: String,
            val quotePrecision: Int,
            val quoteAssetPrecision: Int,
            val orderTypes: List<String>,
            val icebergAllowed: Boolean,
            val ocoAllowed: Boolean,
            val quoteOrderQtyMarketAllowed: Boolean,
            val allowTrailingStop: Boolean,
            val cancelReplaceAllowed: Boolean,
            val isSpotTradingAllowed: Boolean,
            val isMarginTradingAllowed: Boolean,
            val filters: List<Map<String, String>>,
            val permissions: List<String>,
            val defaultSelfTradePreventionMode: String,
            val allowedSelfTradePreventionModes: List<String>
    )
}