package com.oscon.disruptor;

import com.lmax.disruptor.EventHandler;

public class ChefHandler implements EventHandler<OrderEvent> {

    /**
     * {@inheritDoc}
     */
    @Override
    public void onEvent(OrderEvent order, long seq, boolean end)
            throws Exception {
        
    }
}
