package org.example;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        FlottaService flotta = new FlottaService(
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>()
        );

        // 1. Creazione e registrazione veicoli
        Veicolo bici1 = new Bici(101, 80.0, true, 6);
        Veicolo monopattino1 = new MonopattinoElettrico(202, 10.0, true, 25.0); // Batteria < 15%

        flotta.registraVeicolo(bici1);
        flotta.registraVeicolo(monopattino1);

        // 2. Registrazione utenti con controllo Codice Fiscale
        Utente u1 = new Utente("Mario Rossi", 1, "mario@rossi.it", "RSSMRA85M01H501Q", 15.0);
        Utente u2 = new Utente("Luigi Verdi", 2, "luigi@verdi.it", "CODICE_ERRATO", 20.0);

        try {
            flotta.registraUtente(u1);
            System.out.println("Utente registrato: " + u1.getNome());
        } catch (RossiGoException e) {
            System.err.println("Errore registrazione: " + e.getMessage());
        }

        try {
            flotta.registraUtente(u2);
        } catch (CodiceFiscaleNonValidoException e) {
            System.err.println("Errore registrazione: " + e.getMessage());
        }

        // 3. Test sblocco veicolo e calcolo costo
        try {
            flotta.SbloccaVeicolo(bici1);
            System.out.println("Veicolo sbloccato: " + bici1);
            System.out.println("Costo per 20 minuti: " + bici1.CalcolaCosto(20) + " €");
        } catch (RossiGoException e) {
            System.err.println("Errore sblocco: " + e.getMessage());
        }

        try {
            flotta.SbloccaVeicolo(monopattino1);
        } catch (BatteriaScaricaException | VeicoloNonDisponibileException e) {
            System.err.println("Errore sblocco monopattino: " + e.getMessage());
        }
    }
}