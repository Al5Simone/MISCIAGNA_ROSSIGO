package org.example;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Utente> utenti = new ArrayList<>();
        FlottaService flotta = new FlottaService(
                utenti,
                new ArrayList<>(),
                new ArrayList<>()
        );

        Veicolo bici = new Bici(101, 80.0, true, 6);
        Veicolo monopattinoScarico = new MonopattinoElettrico(202, 10.0, true, 25.0);
        flotta.registraVeicolo(bici);
        flotta.registraVeicolo(monopattinoScarico);

        // Verifica del codice fiscale non valido
        Utente utenteNonValido = new Utente("Utente di prova", -1, "prova@example.it", "CODICE_ERRATO", 10.0);
        try {
            flotta.registraUtente(utenteNonValido);
        } catch (CodiceFiscaleNonValidoException e) {
            System.out.println("Eccezione verificata: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Errore database nel test: " + e.getMessage());
        }

        // Inserimento dell'utente nel database
        Utente utente = null;
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Inserisci i dati dell'utente da salvare nel database.");
            System.out.print("ID (univoco): ");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("Codice fiscale: ");
            String codiceFiscale = scanner.nextLine();
            System.out.print("Credito iniziale: ");
            double credito = Double.parseDouble(scanner.nextLine().replace(',', '.'));

            utente = new Utente(nome, id, email, codiceFiscale, credito);
            flotta.registraUtente(utente);
            System.out.println("Utente salvato nella tabella Utenti.");
        } catch (NumberFormatException e) {
            System.err.println("ID o credito non validi: inserisci un numero.");
        } catch (CodiceFiscaleNonValidoException e) {
            System.err.println("Codice fiscale non valido: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Errore durante il salvataggio nel database: " + e.getMessage());
        }

        if (utente == null) {
            return;
        }

        // Verifica della batteria scarica
        try {
            flotta.sbloccaVeicolo(utente, monopattinoScarico, 15);
        } catch (BatteriaScaricaException e) {
            System.out.println("Eccezione verificata: " + e.getMessage());
        } catch (RossiGoException e) {
            System.out.println("Eccezione noleggio: " + e.getMessage());
        }

        // Verifica dell'utente non registrato
        try {
            Utente utenteNonRegistrato = new Utente(
                    "Non registrato",
                    -2,
                    "nonregistrato@example.it",
                    "RSSMRA85M01H501Q",
                    20.0
            );
            flotta.sbloccaVeicolo(utenteNonRegistrato, bici, 16);
        } catch (UtenteNonTrovatoException e) {
            System.out.println("Eccezione verificata: " + e.getMessage());
        } catch (RossiGoException e) {
            System.out.println("Eccezione noleggio: " + e.getMessage());
        }

        // Verifica del credito insufficiente
        List<Utente> utentiTest = new ArrayList<>();
        FlottaService flottaTest = new FlottaService(
                utentiTest,
                new ArrayList<>(),
                new ArrayList<>()
        );
        Veicolo biciTest = new Bici(999, 80.0, true, 6);
        Utente utenteConPocoCredito = new Utente(
                "Test credito",
                -3,
                "test@example.it",
                "RSSMRA85M01H501Q",
                1.0
        );
        utentiTest.add(utenteConPocoCredito);
        flottaTest.registraVeicolo(biciTest);

        try {
            flottaTest.sbloccaVeicolo(utenteConPocoCredito, biciTest, 18);
        } catch (CreditoInsufficienteException e) {
            System.out.println("Eccezione verificata: " + e.getMessage());
        } catch (RossiGoException e) {
            System.out.println("Eccezione noleggio: " + e.getMessage());
        }

        // Noleggio normale della bici
        try {
            Noleggio noleggio = flotta.sbloccaVeicolo(utente, bici, 17);
            System.out.println("Bici sbloccata: " + bici);
            flotta.terminaNoleggio(noleggio, 20);
            System.out.println("Noleggio terminato. Credito residuo: " + utente.getCredito() + " €");
        } catch (RossiGoException e) {
            System.out.println("Eccezione noleggio: " + e.getMessage());
        }
    }
}
