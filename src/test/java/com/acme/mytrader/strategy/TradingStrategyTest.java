package com.acme.mytrader.strategy;

import com.acme.mytrader.price.PriceListener;
import com.acme.mytrader.price.PriceSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TradingStrategyTest {

    @InjectMocks
    private TradingStrategy tradingStrategy;

    @Mock
    private PriceSource priceSource;

    @Mock
    private PriceListener priceListener;

    @Captor
    private ArgumentCaptor<Double> priceCaptor;

    @Test
    public void testProcessStock() {
        double price = 100;
        tradingStrategy.processStock("IBM", price);
        verify(priceSource, times(1)).addPriceListener(any());
        verify(priceListener, times(1)).priceUpdate(anyString(), priceCaptor.capture());
        verify(priceSource, times(1)).removePriceListener(any());
        assertEquals(Double.valueOf(price), priceCaptor.getValue());
    }
}
