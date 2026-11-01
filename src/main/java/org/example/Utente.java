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