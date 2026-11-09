package org.example;

import it.kamaladafrica.codicefiscale.CodiceFiscale;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class FlottaService {

    private static final double SOGLIA_MINIMA_CREDITO = 5.0;
    private static final double SOGLIA_MINIMA_BATTERIA = 15.0;

    private final List<Utente> utenti;
    private final List<Noleggio> noleggi;
    private final List<Veicolo> veicoli;

    public FlottaService(List<Utente> utenti, List<Noleggio> noleggi, List<Veicolo> veicoli) {
        this.utenti = Objects.requireNonNull(utenti, "La lista utenti è obbligatoria");
        this.noleggi = Objects.requireNonNull(noleggi, "La lista noleggi è obbligatoria");
        this.veicoli = Objects.requireNonNull(veicoli, "La lista veicoli è obbligatoria");
    }

    public void registraVeicolo(Veicolo v) {
        veicoli.add(Objects.requireNonNull(v, "Il veicolo è obbligatorio"));
    }

    public void registraUtente(Utente u) throws CodiceFiscaleNonValidoException, SQLException {
        Objects.requireNonNull(u, "L'utente è obbligatorio");
        if (u.getCodiceFiscale() == null || !CodiceFiscale.isFormatValid(u.getCodiceFiscale())) {
            throw new CodiceFiscaleNonValidoException("Errore per " + u.getNome());
        }

        DataBase.getInstance().inserisciUtente(u);
        utenti.add(u);
    }

    public Noleggio sbloccaVeicolo(Utente u, Veicolo v, int oraInizio) throws RossiGoException {
        Objects.requireNonNull(u, "L'utente è obbligatorio");
        Objects.requireNonNull(v, "Il veicolo è obbligatorio");
        if (!utenti.contains(u)) {
            throw new UtenteNonTrovatoException("Utente ID: " + u.getId());
        }
        if (!veicoli.contains(v)) {
            throw new VeicoloNonDisponibileException("Veicolo non registrato: " + v.getCodice());
        }
        if (!v.isDisponibile()) {
            throw new VeicoloNonDisponibileException("Veicolo codice: " + v.getCodice());
        }
        if (v.getLivelloBatteria() < SOGLIA_MINIMA_BATTERIA) {
            throw new BatteriaScaricaException("Veicolo codice: " + v.getCodice());
        }
        if (u.getCredito() < SOGLIA_MINIMA_CREDITO) {
            throw new CreditoInsufficienteException("Utente: " + u.getNome());
        }

        v.setStato(false); // Veicolo ora in uso
        Noleggio n = new Noleggio(u, v, oraInizio, -1);
        noleggi.add(n);
        return n;
    }

    public void terminaNoleggio(Noleggio n, int minutiUtilizzo) throws RossiGoException {
        Objects.requireNonNull(n, "Il noleggio è obbligatorio");
        if (!noleggi.contains(n) || n.getVeicolo().isDisponibile()) {
            throw new VeicoloNonDisponibileException("Il noleggio non è attivo");
        }
        Veicolo v = n.getVeicolo();
        Utente u = n.getUtente();

        double costo = v.CalcolaCosto(minutiUtilizzo);
        if (u.getCredito() < costo) {
            throw new CreditoInsufficienteException("Credito insufficiente per terminare il noleggio");
        }
        u.scalaCredito(costo);

        double batteriaConsumata = v.ConsumoBatteria() * minutiUtilizzo;
        double nuovaBatteria = Math.max(0, v.getLivelloBatteria() - batteriaConsumata);
        v.setLivelloBatteria(nuovaBatteria);

        v.setStato(true); // Veicolo di nuovo disponibile
    }
}