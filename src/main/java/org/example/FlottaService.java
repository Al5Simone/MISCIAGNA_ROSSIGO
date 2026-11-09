package org.example;

import it.kamaladafrica.codicefiscale.CodiceFiscale;

import java.util.List;
import java.util.Objects;

public class FlottaService {

    private final List<Utente> utenti;
    private final List<Noleggio> noleggi;
    private final List<Veicolo> veicoli;

    public FlottaService(List<Utente> utenti, List<Noleggio> noleggi, List<Veicolo> veicoli) {
        this.utenti = Objects.requireNonNull(utenti, "La lista utenti è obbligatoria");
        this.noleggi = Objects.requireNonNull(noleggi, "La lista noleggi è obbligatoria");
        this.veicoli = Objects.requireNonNull(veicoli, "La lista veicoli è obbligatoria");
    }

    public void registraVeicolo(Veicolo veicolo) {
        this.veicoli.add(Objects.requireNonNull(veicolo, "Il veicolo è obbligatorio"));
    }

    public void registraUtente(Utente utente) throws CodiceFiscaleNonValidoException {
        Objects.requireNonNull(utente, "L'utente è obbligatorio");
        String codiceFiscale = utente.getCodiceFiscale();
        if (codiceFiscale == null || !CodiceFiscale.isFormatValid(codiceFiscale)) {
            throw new CodiceFiscaleNonValidoException("Codice fiscale non valido per " + utente.getNome());
        }
        this.utenti.add(utente);
    }

    public void SbloccaVeicolo(Veicolo veicolo)
            throws VeicoloNonDisponibileException, BatteriaScaricaException {
        if (veicolo == null || !veicoli.contains(veicolo) || !veicolo.isDisponibile()) {
            throw new VeicoloNonDisponibileException("Veicolo non registrato o non disponibile");
        }
        if (veicolo.getLivelloBatteria() < 15) {
            throw new BatteriaScaricaException("Impossibile sbloccare il veicolo " + veicolo.getCodice());
        }
        veicolo.setDisponibile(false);
    }
}