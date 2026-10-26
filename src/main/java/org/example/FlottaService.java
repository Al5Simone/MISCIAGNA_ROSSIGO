package org.example;

import it.kamaladafrica.codicefiscale.CodiceFiscale;

import java.util.List;

public class FlottaService {

    private static List<Utente> utente;
    private static List<Noleggio> noleggio;
    private static List<Veicolo> veicolo;

    public FlottaService(List<Utente> utente, List<Noleggio> noleggio, List<Veicolo> veicolo) {
        FlottaService.utente = utente;
        FlottaService.noleggio = noleggio;
        FlottaService.veicolo = veicolo;
    }

    public static void registraVeicolo(Veicolo v) {
        veicolo.add(v);
    }

    public static void registraUtente(Utente u) {
        CodiceFiscale.isFormatValid(u.getCodiceFiscale());
        utente.add(u);
    }

    public static void SbloccaVeicolo(Veicolo v) {
        veicolo.remove(v);
    }
}