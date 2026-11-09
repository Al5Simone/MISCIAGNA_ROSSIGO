package org.example;

public class MonopattinoElettrico extends Veicolo{

    private final double velocitaMassima;

    public MonopattinoElettrico(int codice, double livelloBatteria, boolean stato, double velocitaMassima) {
        super(codice, livelloBatteria, stato);
        if (!Double.isFinite(velocitaMassima) || velocitaMassima <= 0) {
            throw new IllegalArgumentException("La velocità massima deve essere positiva");
        }
        this.velocitaMassima = velocitaMassima;
    }

    @Override
    public double CalcolaCosto(int minuti) {
        verificaDurata(minuti);
        return minuti * 0.25;
    }

    @Override
    public double ConsumoBatteria() {
        return 1;
    }

    @Override
    public String toString() {
        return "MonopattinoElettrico{" + "velocitaMassima=" + velocitaMassima
                + " km/h, consumoBatteria=" + ConsumoBatteria()
                + "%/min, " + super.toString() + "}";
    }
}
