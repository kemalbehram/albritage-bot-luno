package org.example.exchanges.luno.dto

data class GetFeeInformationDto(
        val thirty_day_volume: Double,
        val maker_fee: Double,
        val taker_fee: Double
)
