package com.ism.repositories.bd.impl;

import com.ism.entities.AbstractEntity;
import com.ism.entities.Patient;
import com.ism.repositories.bd.MySQLRepository;
import com.ism.repositories.bd.PatientRepository;
import com.ism.repositories.core.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PatientRepositoryImpl extends MySQLRepository<Patient> implements PatientRepository {
    public PatientRepositoryImpl(Database database) {
    }

    @Override
    public int insert(AbstractEntity data) {
        int nbrLigne = 0;
        try (Connection conn = getConnection()) {
            Patient patient = (Patient) data;
            String SQL_INSERT = "INSERT INTO `patients` (`id`, `nom`, `prenom`, `email`, `telephone`) VALUES (NULL, ?, ?, ?, ?)";
            PreparedStatement statement = prepareStatement(conn, SQL_INSERT, patient.getNom(), patient.getPrenom(), patient.getEmail(), patient.getTelephone());
            nbrLigne = executeUpdate(conn, statement);
            closeConnection(conn, statement);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return nbrLigne;
    }

    @Override
    public int update(AbstractEntity data) {
        int nbrLigne = 0;
        try (Connection conn = getConnection()) {
            String SQL_UPDATE = "UPDATE `patients` SET `nom` = ?, `prenom` = ?, `email` = ?, `telephone` = ? WHERE `id` = ?";
            PreparedStatement statement = prepareStatement(conn, SQL_UPDATE, data.getNom(), ((Patient) data).getPrenom(), ((Patient) data).getEmail(), ((Patient) data).getTelephone(), data.getId());
            nbrLigne = executeUpdate(conn, statement);
            closeConnection(conn, statement);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return nbrLigne;
    }

    @Override
    public ArrayList<AbstractEntity> findAll() {
        ArrayList<AbstractEntity> resultList = new ArrayList<>();
        try (Connection conn = getConnection()) {
            String SQL_FIND_ALL = "SELECT id, nom, prenom, email, telephone FROM patients";
            PreparedStatement statement = prepareStatement(conn, SQL_FIND_ALL);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                // ORM == convertit une ligne de base de données en un objet de type Java
                Patient entity = new Patient(
                        // Convertit le type BD en type Java
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("telephone")
                );
                resultList.add(entity);
            }
            closeConnection(conn, statement, rs);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    @Override
    public AbstractEntity findByID(int id) {
        Patient entity = null;
        try (Connection conn = getConnection()) {
            String SQL_FIND = "SELECT id, nom, prenom, email, telephone FROM patients WHERE id = ?";
            PreparedStatement statement = prepareStatement(conn, SQL_FIND, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                entity = new Patient(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("telephone")
                );
            }
            closeConnection(conn, statement, rs);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entity;
    }

    @Override
    public int delete(int id) {
        int nbrLigne = 0;
        try (Connection conn = getConnection()) {
            String SQL_DELETE = "DELETE FROM `patients` WHERE `id` = ?";
            PreparedStatement statement = prepareStatement(conn, SQL_DELETE, id);
            nbrLigne = executeUpdate(conn, statement);
            closeConnection(conn, statement);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return nbrLigne;
    }
}
