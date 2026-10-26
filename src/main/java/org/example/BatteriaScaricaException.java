package org.example;

public class BatteriaScaricaException extends RossiGoException {
    public BatteriaScaricaException(String message) {
        super(message +" \n batteria sotto il 15%");
    }
}
