package org.example;

public class UtenteNonTrovatoException extends RossiGoException {
    public UtenteNonTrovatoException(String message) {
        super(message + "\n l'id utente non esiste\n");
    }
}
