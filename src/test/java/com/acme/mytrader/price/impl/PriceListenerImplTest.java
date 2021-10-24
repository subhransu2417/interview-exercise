package com.acme.mytrader.price.impl;

import com.acme.mytrader.execution.ExecutionService;
import com.acme.mytrader.price.PriceListener;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PriceListenerImplTest {

    @InjectMocks
    private PriceListener priceListener = new PriceListenerImpl(55, 100);

    @Mock
    private ExecutionService executionService;

    @Test
    public void testPriceUpdate_PriceLessThanThreshold_ShouldBuy() {
        priceListener.priceUpdate("IBM", 90);
        verify(executionService, times(1)).buy(anyString(), anyDouble(), anyInt());
    }

    @Test
    public void testPriceUpdate_PriceEqualsToThreshold_ShouldNotBuy() {
        priceListener.priceUpdate("IBM", 100);
        verify(executionService, times(0)).buy(anyString(), anyDouble(), anyInt());
    }

    @Test
    public void testPriceUpdate_PriceGreaterThanThreshold_ShouldNotBuy() {
        priceListener.priceUpdate("IBM", 100);
        verify(executionService, times(0)).buy(anyString(), anyDouble(), anyInt());
    }

    @Test
    public void testPriceUpdate_PriceIsNegetive_ShouldNotBuy() {
        priceListener.priceUpdate("IBM", -1);
        verify(executionService, times(0)).buy(anyString(), anyDouble(), anyInt());
    }

    @Test
    public void testPriceUpdate_SecurityNull_ShouldNotBuy() {
        priceListener.priceUpdate(null, 55);
        verify(executionService, times(0)).buy(anyString(), anyDouble(), anyInt());
    }

    @Test
    public void testPriceUpdate_SecurityEmpty_ShouldNotBuy() {
        priceListener.priceUpdate("", 56);
        verify(executionService, times(0)).buy(anyString(), anyDouble(), anyInt());
    }
}