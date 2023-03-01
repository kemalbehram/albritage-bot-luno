package org.example.exchanges.luno.dto

data class ListOrdersDto(
        val orders: List<Order>
) {
    data class Order (
            val base: String,
            val completed_timestamp: String,
            val counter: String,
            val creation_timestamp: String,
            val expiration_timestamp: String,
            val fee_base: String,
            val fee_counter: String,
            val limit_price: String,
            val limit_volume: String,
            val order_id: String,
            val pair: String,
            val state: String,
            val time_in_force: String,
            val type: String
    )
}
