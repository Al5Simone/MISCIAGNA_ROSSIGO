package org.example;

public class Bici extends Veicolo {

    private final int marce;

    public Bici(int codice, double livelloBatteria, boolean stato, int marce) {
        super(codice, livelloBatteria, stato);
        if (marce <= 0) {
            throw new IllegalArgumentException("Il numero di marce deve essere positivo");
        }
        this.marce = marce;
    }

    public int NumeroMarce() {
        return marce;
    }

    @Override
    public double CalcolaCosto(int minuti) {
        verificaDurata(minuti);
        return 0.15 * minuti;
    }

    @Override
    public double ConsumoBatteria() {
        return 0.5;
    }

    @Override
    public String toString() {
        return "Bici{" + "marce=" + marce + ", consumoBatteria=" + ConsumoBatteria()
                + "%/min, " + super.toString() + "}";
    }
}
