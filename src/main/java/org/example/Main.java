package org.example;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        List<Utente> utenti = new ArrayList<>();
        List<Noleggio> noleggi = new ArrayList<>();
        List<Veicolo> veicoli = new ArrayList<>();

        // 2. CREA IL SERVIZIO PASSANDOGLI LE LISTE
        FlottaService service = new FlottaService(utenti, noleggi, veicoli);


        Veicolo m2 = new Bici(456,12, true,6);

        FlottaService.registraVeicolo(m2);
       /* //Eccezzione utente
        try{
            Utente u1 = new Utente("Simone", 10294, "simone@gmail.com", "CF_ERRATO_123", 0.20);
            FlottaService.registraUtente(u1);
            FlottaService.SbloccaVeicolo("10294","124");
        }catch(RossiGoException e){
            System.out.println("Eccezzione: " + e.getMessage());
        }
        //eccezzione credito
        try{
            Utente u2 = new Utente("Simone", 10294, "simone@gmail.com", "RSSMRA80A01F205X", 0.20);
            FlottaService.registraUtente(u2);
            FlottaService.SbloccaVeicolo("124","10294");
        }catch(RossiGoException e){
            System.out.println("Eccezzione: " + e.getMessage());
        }*/
        //eccezzione veicolo
        try {
            Veicolo m1 = new MonopattinoElettrico(123, 80, false, 30);
            FlottaService.registraVeicolo(m1);
            Utente u3 = new Utente("Simone", 99999, "simone@gmail.com", "RSSMRA80A01F205X", 10.20);
            FlottaService.registraUtente(u3);

            FlottaService.SbloccaVeicolo("124", "99999");

        } catch (RossiGoException e) {
            System.out.println("Eccezione: " + e.getMessage());
        }
    }
}