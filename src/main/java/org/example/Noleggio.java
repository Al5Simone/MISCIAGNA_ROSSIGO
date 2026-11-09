package org.example;

public class Noleggio {

    private final String utente;
    private final Veicolo veicolo;
    private final int oraInizio;
    private final int oraFine;

    public Noleggio(String utente, Veicolo veicolo, int oraInizio, int oraFine) {
        if (utente == null || utente.isBlank()) {
            throw new IllegalArgumentException("L'utente è obbligatorio");
        }
        if (veicolo == null) {
            throw new IllegalArgumentException("Il veicolo è obbligatorio");
        }
        if (oraFine <= oraInizio) {
            throw new IllegalArgumentException("L'ora di fine deve essere successiva all'ora di inizio");
        }
        this.utente = utente;
        this.veicolo = veicolo;
        this.oraInizio = oraInizio;
        this.oraFine = oraFine;
    }

    public String getUtente() {
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
