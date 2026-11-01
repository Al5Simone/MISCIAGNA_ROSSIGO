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

    public static void registraUtente(Utente u) throws CodiceFiscaleNonValidoException {
       if(!CodiceFiscale.isFormatValid(u.getCodiceFiscale())){
           throw new CodiceFiscaleNonValidoException(u.getCodiceFiscale());
       };
        utente.add(u);
    }

    public static void SbloccaVeicolo(String codice, String IdUtente) throws UtenteNonTrovatoException,  CreditoInsufficienteException,VeicoloNonDisponibileException,BatteriaScaricaException {
        //Controllo per verificare l'utente
        Utente utenteTrovato = null;
        int IDCercato = Integer.parseInt(IdUtente);
        for(Utente u : utente){
            if(u.getId() == IDCercato) {
                utenteTrovato = u;
                break;
            }
        }
        if(utenteTrovato == null){
            throw new UtenteNonTrovatoException("Utente non trovato");
        }
        //Controllo per verificare il credito dell'utente
        if(utenteTrovato.getCredito() < 1.0){
            throw new CreditoInsufficienteException("credito insufficiente");
        }
        //Controllo per verificare il  Veicolo
        Veicolo veicoloTrovato = null;
        if(veicoloTrovato == null || veicoloTrovato.stato == true){
            throw new VeicoloNonDisponibileException("veicolo non trovato");
        }
        //Controlllo per verificare la batteria
        if (veicoloTrovato.livelloBatteria < 15) {
            throw new BatteriaScaricaException("batteria insufficiente");
        }

    }

    public static void TerminaNoleggio(String codice, int minuti) throws UtenteNonTrovatoException, VeicoloNonDisponibileException {
        Utente utenteTrovato = null;
        for(Utente u : utente){
            if(u.getCodiceFiscale().equals(codice)){
                utenteTrovato = u;
                break;
            }
        }
        if(utenteTrovato == null){
            throw new UtenteNonTrovatoException("Utente non trovato");
        }
        Veicolo veicoloTrovato = null;
        if(veicoloTrovato == null || veicoloTrovato.stato == true){
            throw new VeicoloNonDisponibileException("veicolo non trovato o occupato");
        }
        double costoTotale = 1.0 + veicoloTrovato.CalcolaCosto(minuti);
        utenteTrovato.setCredito(utenteTrovato.getCredito() - costoTotale);
        veicoloTrovato.ConsumoBatteria();
    }

    public static void MettiInRicarica(String Codice) throws BatteriaScaricaException {
        Veicolo v = null;
        if (v.livelloBatteria < 15) {
            throw new BatteriaScaricaException("batteria insufficiente");
        }

    }
}
