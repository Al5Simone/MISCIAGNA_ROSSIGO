package org.example;

import java.util.Locale;

public class Utente {

    private final String nome;
    private final int id;
    private final String email;
    private final String codiceFiscale;
    private final double credito;

    public Utente(String nome, int id, String email, String codiceFiscale, double credito) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Il nome è obbligatorio");
        }
        if (id <= 0) {
            throw new IllegalArgumentException("L'id deve essere positivo");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("L'email è obbligatoria");
        }
        if (Double.isNaN(credito) || credito < 0) {
            throw new IllegalArgumentException("Il credito non può essere negativo");
        }
        this.nome = nome;
        this.id = id;
        this.email = email;
        this.codiceFiscale = codiceFiscale == null ? null : codiceFiscale.trim().toUpperCase(Locale.ROOT);
        this.credito = credito;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public void setCredito(double credito) {
        this.credito = credito;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public double getCredito() {
        return credito;
    }
}