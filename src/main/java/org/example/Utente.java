package org.example;

public class Utente {

    private String nome;
    private int id;
    private String email;
    private String codiceFiscale;
    private double credito;

    public Utente(String nome, int id, String email, String codiceFiscale, double credito) {
        this.nome = nome;
        this.id = id;
        this.email = email;
        this.codiceFiscale = codiceFiscale;
        this.credito = credito;
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