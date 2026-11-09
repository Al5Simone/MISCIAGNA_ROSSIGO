package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataBase {
    private static DataBase instance;
    private final Connection connection;

    private DataBase() throws SQLException {
        String url = "jdbc:sqlite:rossigo.db";
        connection = DriverManager.getConnection(url);
    }

    public static synchronized DataBase getInstance() throws SQLException {
        if (instance == null) {
            instance = new DataBase();
        }
        return instance;
    }

    public void inserisciUtente(Utente utente) throws SQLException {
        String sql = """
                INSERT INTO Utenti (id, nome, email, Codice_Fiscale, Credito)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, String.valueOf(utente.getId()));
            statement.setString(2, utente.getNome());
            statement.setString(3, utente.getEmail());
            statement.setString(4, utente.getCodiceFiscale());
            statement.setDouble(5, utente.getCredito());
            statement.executeUpdate();
        }
    }
}
