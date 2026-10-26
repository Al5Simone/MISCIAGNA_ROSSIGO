package org.example;

public class MonopattinoElettrico extends Veicolo{

    private double velocitaMassima;

    public MonopattinoElettrico(int codice, double livelloBatteria, boolean stato, double velocitaMassima) {
        super(codice, livelloBatteria, stato);
        this.velocitaMassima = velocitaMassima;
    }


    @Override
    double CalcolaCosto(int minuti) {
        return minuti * 0.25;
    }

    @Override
    double ConsumoBatteria() {
        return 1;
    }

    @Override
    public  String toString(){

        return "MonopattinoElettrico{" + "VelocitàMassima: " + velocitaMassima + " e consuma: " + ConsumoBatteria() + super.toString() + "}";
    }
}
