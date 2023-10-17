package com.ism.repositories.bd.impl;

import com.ism.entities.AbstractEntity;
import com.ism.entities.RendezVous;
import com.ism.repositories.bd.MySQLRepository;
import com.ism.repositories.bd.RendezVousRepository;
import com.ism.repositories.core.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RendezVousRepositoryImpl extends MySQLRepository<RendezVous> implements RendezVousRepository {
    public RendezVousRepositoryImpl(Database database) {
    }

    @Override
    public int insert(AbstractEntity data) {
        int nbrLigne = 0;
        try (Connection conn = getConnection()) {
            RendezVous rendezVous = (RendezVous) data;
            String SQL_INSERT = "INSERT INTO `rendez_vous` (`id`, `date`, `heure`, `patient_id`, `medecin_id`) VALUES (NULL, ?, ?, ?, ?)";
            PreparedStatement statement = prepareStatement(conn, SQL_INSERT, rendezVous.getDate(), rendezVous.getHeure(), rendezVous.getPatientId(), rendezVous.getMedecinId());
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
            String SQL_UPDATE = "UPDATE `rendez_vous` SET `date` = ?, `heure` = ?, `patient_id` = ?, `medecin_id` = ? WHERE `id` = ?";
            PreparedStatement statement = prepareStatement(conn, SQL_UPDATE, ((RendezVous) data).getDate(), ((RendezVous) data).getHeure(), ((RendezVous) data).getPatientId(), ((RendezVous) data).getMedecinId(), data.getId());
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
            String SQL_FIND_ALL = "SELECT rv.id, rv.date, rv.heure, rv.patient_id, rv.medecin_id, p.nom AS patient_nom, m.nom AS medecin_nom " +
                    "FROM rendez_vous rv " +
                    "INNER JOIN patients p ON rv.patient_id = p.id " +
                    "INNER JOIN medecins m ON rv.medecin_id = m.id";
            PreparedStatement statement = prepareStatement(conn, SQL_FIND_ALL);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                // ORM == convertit une ligne de base de données en un objet de type Java
                RendezVous entity = new RendezVous(
                        // Convertit le type BD en type Java
                        rs.getInt("id"),
                        rs.getDate("date").toLocalDate(),
                        rs.getTime("heure").toLocalTime(),
                        rs.getInt("patient_id"),
                        rs.getInt("medecin_id"),
                        rs.getString("patient_nom"),
                        rs.getString("medecin_nom")
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
        RendezVous entity = null;
        try (Connection conn = getConnection()) {
            String SQL_FIND = "SELECT rv.id, rv.date, rv.heure, rv.patient_id, rv.medecin_id, p.nom AS patient_nom, m.nom AS medecin_nom " +
                    "FROM rendez_vous rv " +
                    "INNER JOIN patients p ON rv.patient_id = p.id " +
                    "INNER JOIN medecins m ON rv.medecin_id = m.id " +
                    "WHERE rv.id = ?";
            PreparedStatement statement = prepareStatement(conn, SQL_FIND, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                entity = new RendezVous(
                        rs.getInt("id"),
                        rs.getDate("date").toLocalDate(),
                        rs.getTime("heure").toLocalTime(),
                        rs.getInt("patient_id"),
                        rs.getInt("medecin_id"),
                        rs.getString("patient_nom"),
                        rs.getString("medecin_nom")
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
            String SQL_DELETE = "DELETE FROM `rendez_vous` WHERE `id` = ?";
            PreparedStatement statement = prepareStatement(conn, SQL_DELETE, id);
            nbrLigne = executeUpdate(conn, statement);
            closeConnection(conn, statement);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return nbrLigne;
    }
}
