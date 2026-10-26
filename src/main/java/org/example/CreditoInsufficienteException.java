package org.example;

public class CreditoInsufficienteException extends RossiGoException {
    public CreditoInsufficienteException(String message) {
        super(message+ " \n credito sotto la soglia di sblocco");
    }
}
