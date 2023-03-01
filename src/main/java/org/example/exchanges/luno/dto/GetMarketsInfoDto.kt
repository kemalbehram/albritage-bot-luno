package org.example.exchanges.luno.dto

data class GetMarketsInfoDto(
        val markets: List<Market>
) {
    data class Market (
            val market_id: String,
            val trading_status: String,
            val base_currency: String,
            val counter_currency: String,
            val min_volume: String,
            val max_volume: String,
            val volume_scale: Long,
            val min_price: String,
            val max_price: String,
            val price_scale: Long,
            val fee_scale: Long
    )
}
