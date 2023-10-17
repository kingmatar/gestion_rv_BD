package com.ism.repositories.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLImpl implements Database {
    private final String driver = "com.mysql.cj.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost:3306/base_donnee_rv"; // Remplacez 'database' par le nom de votre base de données.
    private final String user = "root";
    private final String password = "";
    private Connection conn = null;
    private PreparedStatement ps;

    @Override
    public PreparedStatement getPs() {
        return ps;
    }

    @Override
    public void openConnection() {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.printf("Erreur de chargement du driver %s", MySQLImpl.class);
        }
    }

    @Override
    public void closeConnexion() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("Erreur de fermeture de la connexion");
            }
        }
    }

    @Override
    public ResultSet executeSelect(String sql) {
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            System.out.println("Erreur d'exécution de la requête SELECT");
        }
        return rs;
    }

    @Override
    public int executeUpdate(String sql) {
        int nbrLigne = 0;
        try {
            ps = conn.prepareStatement(sql);
            nbrLigne = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur d'exécution de la requête UPDATE");
        }
        return nbrLigne;
    }
}

