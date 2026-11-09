package org.example;

public class Noleggio {

    private Utente utente;
    private Veicolo veicolo;
    private int oraInizio;
    private int oraFine;

    public Noleggio(Utente utente, Veicolo veicolo, int oraInizio, int oraFine) {
        this.utente = utente;
        this.veicolo = veicolo;
        this.oraInizio = oraInizio;
        this.oraFine = oraFine;
    }

    public Utente getUtente() {
        return utente;
    }

    public Veicolo getVeicolo() {
        return veicolo;
    }

    public int getOraInizio() {
        return oraInizio;
    }

    public int getOraFine() {
        return oraFine;
    }
}