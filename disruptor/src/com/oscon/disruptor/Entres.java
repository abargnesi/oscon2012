package com.oscon.disruptor;

public enum Entres {
    HAMBURGER("Hamburger"),
    HOT_DOG("Hot Dog"),
    CHICKEN_FINGERS("Chicken Fingers"),
    QUESADILLA("Quesadilla");
    
    private final String label;
    
    private Entres(final String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
