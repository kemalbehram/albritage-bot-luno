package org.example.exchanges.binance.converter;

import org.example.domain.models.MainStateModel;
import org.example.exchanges.binance.dto.OrderBookResponseDto;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class OrderBookConverter {
    public static MainStateModel.ExchangeData.CoinData.OrderBook orderBookConverter(OrderBookResponseDto orderBookDto) {
        LinkedList<MainStateModel.ExchangeData.CoinData.OrderBook.OrderBookItem> bids = new LinkedList<>();
        LinkedList<MainStateModel.ExchangeData.CoinData.OrderBook.OrderBookItem> ask = new LinkedList<>();


        for(List<String> symbol : orderBookDto.getBids()) {
            bids.add(
                    new MainStateModel.ExchangeData.CoinData.OrderBook.OrderBookItem(
                            new BigDecimal(symbol.get(0)),
                            new BigDecimal(symbol.get(1))
                    )
            );
        }
        for(List<String> symbol : orderBookDto.getAsks()) {
            ask.add(
                    new MainStateModel.ExchangeData.CoinData.OrderBook.OrderBookItem(
                            new BigDecimal(symbol.get(0)),
                            new BigDecimal(symbol.get(1))
                    )
            );
        }

        return new MainStateModel.ExchangeData.CoinData.OrderBook(bids, ask);
    }
}
