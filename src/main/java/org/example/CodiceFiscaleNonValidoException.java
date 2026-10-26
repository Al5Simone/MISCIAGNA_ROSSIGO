package org.example;

public class CodiceFiscaleNonValidoException extends RossiGoException {
    public CodiceFiscaleNonValidoException(String message) {
        super(message + "\nil codice fiscale fornito in registrazione non è valido");
    }
}
