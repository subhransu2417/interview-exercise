package com.acme.mytrader.price.impl;

import com.acme.mytrader.execution.ExecutionService;
import com.acme.mytrader.price.PriceListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class PriceListenerImpl implements PriceListener {

    @Autowired
    private ExecutionService executionService;

    private int lotSize;

    private Predicate<Double> priceMatcher;

    public PriceListenerImpl(int lotSize, double priceThreshold) {
        this.lotSize = lotSize;
        priceMatcher = price -> price > 0 && price < priceThreshold;
    }

    @Override
    public void priceUpdate(String security, double price) {
        if (!isEmpty(security) && priceMatcher.test(price)) {
            executionService.buy(security, price, lotSize);
        }
    }

    private boolean isEmpty(String security) {
        return security == null || "".equals(security);
    }
}
