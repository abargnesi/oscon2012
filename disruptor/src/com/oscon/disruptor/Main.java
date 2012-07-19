package com.oscon.disruptor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

public class Main {
    
    private final static int NUM_ORDERS = 100000;
    private final static int RING_SIZE = 16;
    public static final ExecutorService EXECUTOR = Executors
            .newFixedThreadPool(6);

    public static void main(String[] args) {
        final Disruptor<OrderEvent> disruptor = new Disruptor<OrderEvent>(
                OrderEvent.FACTORY, RING_SIZE, EXECUTOR);
        final RingBuffer<OrderEvent> ringBuffer = disruptor.start();
        
        
    }
}
