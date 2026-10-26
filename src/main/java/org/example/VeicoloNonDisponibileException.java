package org.example;

public class VeicoloNonDisponibileException extends RossiGoException {
    public VeicoloNonDisponibileException(String message) {
        super(message + "\n il veicolo è già in uso o in ricarica");
    }
}
