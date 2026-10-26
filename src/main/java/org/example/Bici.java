package org.example;

public class Bici extends Veicolo{

    private int marce;


    public Bici(int codice, double livelloBatteria, boolean stato, int marce) {
        super(codice, livelloBatteria, stato);
        this.marce = marce;
    }

    public int NumeroMarce(){
        return marce;
    }

    @Override
     public double CalcolaCosto(int minuti){

        return  0.15* minuti;

     }

     @Override
    public double ConsumoBatteria(){
        return  0.5;
    }

    @Override
    public  String toString(){

        return "Bici{" + "marce=" + marce + " e consuma: " + ConsumoBatteria() + super.toString() + "}";
    }
}
