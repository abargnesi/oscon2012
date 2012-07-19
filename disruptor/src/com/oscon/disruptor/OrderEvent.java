package com.oscon.disruptor;

import java.util.List;

import com.lmax.disruptor.EventFactory;

public class OrderEvent {

    private int number;
    private List<Entres> items;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    
    public List<Entres> getItems() {
        return items;
    }

    public void setItems(List<Entres> items) {
        this.items = items;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + number;
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        OrderEvent other = (OrderEvent) obj;
        if (number != other.number)
            return false;
        return true;
    }
    
    public final static EventFactory<OrderEvent> FACTORY = new EventFactory<OrderEvent>() {

        @Override
        public OrderEvent newInstance() {
            return new OrderEvent();
        }
    };
}
