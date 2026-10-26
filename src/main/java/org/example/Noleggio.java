package org.example;

public class Noleggio {

    private String utente;
    private Veicolo veicolo;
    private int oraInizio;
    private int oraFine;

    public Noleggio(String utente, Veicolo veicolo, int oraInizio, int oraFine) {
        this.utente = utente;
        this.veicolo = veicolo;
        this.oraInizio = oraInizio;
        this.oraFine = oraFine;
    }
}
