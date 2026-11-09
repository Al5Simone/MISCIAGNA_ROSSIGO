package org.example;

public abstract class Veicolo {

    private final int codice;
    protected double livelloBatteria;
    protected boolean stato;

    public Veicolo(int codice, double livelloBatteria, boolean stato) {
        if (codice <= 0) {
            throw new IllegalArgumentException("Il codice del veicolo deve essere positivo");
        }
        if (Double.isNaN(livelloBatteria) || livelloBatteria < 0 || livelloBatteria > 100) {
            throw new IllegalArgumentException("Il livello della batteria deve essere tra 0 e 100");
        }
        this.codice = codice;
        this.livelloBatteria = livelloBatteria;
        this.stato = stato;
    }

    public int getCodice() {
        return codice;
    }

    public double getLivelloBatteria() {
        return livelloBatteria;
    }

    public boolean isDisponibile() {
        return stato;
    }

    public void setDisponibile(boolean disponibile) {
        stato = disponibile;
    }

    protected void verificaDurata(int minuti) {
        if (minuti <= 0) {
            throw new IllegalArgumentException("La durata deve essere maggiore di zero");
        }
    }

    public abstract double CalcolaCosto(int minuti);

    // in % al minuto
    public abstract double ConsumoBatteria();

    @Override
    public String toString() {
        return "codice=" + codice
                + ", livelloBatteria=" + livelloBatteria
                + "%, disponibile=" + stato;
    }

}
