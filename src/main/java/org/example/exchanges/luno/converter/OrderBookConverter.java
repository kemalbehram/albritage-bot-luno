package org.example.exchanges.luno.converter;

import org.example.domain.models.MainStateModel;
import org.example.exchanges.luno.dto.GetFullOrderBookDto;

import java.math.BigDecimal;
import java.util.LinkedList;

public class OrderBookConverter {
    public static MainStateModel.ExchangeData.CoinData.OrderBook orderBookConverter(GetFullOrderBookDto dto) {
        LinkedList<MainStateModel.ExchangeData.CoinData.OrderBook.OrderBookItem> bids = new LinkedList<>();
        LinkedList<MainStateModel.ExchangeData.CoinData.OrderBook.OrderBookItem> asks = new LinkedList<>();

        for(GetFullOrderBookDto.Ask bid : dto.getBids()) {
            bids.add(
                    new MainStateModel.ExchangeData.CoinData.OrderBook.OrderBookItem(
                            new BigDecimal(bid.getPrice()),
                            new BigDecimal(bid.getVolume())
                    )
            );
        }

        for(GetFullOrderBookDto.Ask ask : dto.getAsks()) {
            asks.add(
                    new MainStateModel.ExchangeData.CoinData.OrderBook.OrderBookItem(
                            new BigDecimal(ask.getPrice()),
                            new BigDecimal(ask.getVolume())
                    )
            );
        }

        return new MainStateModel.ExchangeData.CoinData.OrderBook(asks, bids);
    }
}
