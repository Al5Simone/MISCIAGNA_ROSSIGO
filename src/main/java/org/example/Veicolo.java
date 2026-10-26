package org.example;

public abstract class Veicolo {

    private int codice;
    protected double livelloBatteria;
    protected boolean stato;

    public Veicolo(int codice, double livelloBatteria, boolean stato) {
        this.codice = codice;
        this.livelloBatteria = livelloBatteria;
        this.stato = stato;
    }

    abstract double CalcolaCosto(int minuti);

    // in % al minuto
    abstract double ConsumoBatteria();

}
