package com.acme.mytrader.strategy;

import com.acme.mytrader.price.PriceListener;
import com.acme.mytrader.price.PriceSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <pre>
 * User Story: As a trader I want to be able to monitor stock prices such
 * that when they breach a trigger level orders can be executed automatically
 * </pre>
 */
@Service
public class TradingStrategy {

    @Autowired
    private PriceSource priceSource;

    @Autowired
    private PriceListener priceListener;

    public void processStock(String security, double price) {
        priceSource.addPriceListener(priceListener);
        priceListener.priceUpdate(security, price);
        priceSource.removePriceListener(priceListener);
    }

}
