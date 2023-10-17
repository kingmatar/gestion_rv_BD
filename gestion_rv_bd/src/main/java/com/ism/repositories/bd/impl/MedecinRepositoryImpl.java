package com.ism.repositories.bd.impl;

import com.ism.entities.AbstractEntity;
import com.ism.entities.Medecin;
import com.ism.repositories.bd.MedecinRepository;
import com.ism.repositories.bd.MySQLRepository;
import com.ism.repositories.core.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MedecinRepositoryImpl extends MySQLRepository<Medecin> implements MedecinRepository {
    public MedecinRepositoryImpl(Database database) {
    }

    @Override
    public int insert(AbstractEntity data) {
        int nbrLigne = 0;
        try (Connection conn = getConnection()) {
            Medecin medecin = (Medecin) data;
            String SQL_INSERT = "INSERT INTO `medecins` (`id`, `nom`, `prenom`, `specialite`, `email`, `telephone`) VALUES (NULL, ?, ?, ?, ?, ?)";
            PreparedStatement statement = prepareStatement(conn, SQL_INSERT, medecin.getNom(), medecin.getPrenom(), medecin.getSpecialite(), medecin.getEmail(), medecin.getTelephone());
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
            String SQL_UPDATE = "UPDATE `medecins` SET `nom` = ?, `prenom` = ?, `specialite` = ?, `email` = ?, `telephone` = ? WHERE `id` = ?";
            PreparedStatement statement = prepareStatement(conn, SQL_UPDATE, data.getNom(), ((Medecin) data).getPrenom(), ((Medecin) data).getSpecialite(), ((Medecin) data).getEmail(), ((Medecin) data).getTelephone(), data.getId());
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
            String SQL_FIND_ALL = "SELECT id, nom, prenom, specialite, email, telephone FROM medecins";
            PreparedStatement statement = prepareStatement(conn, SQL_FIND_ALL);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                // ORM == convertit une ligne de base de données en un objet de type Java
                Medecin entity = new Medecin(
                        // Convertit le type BD en type Java
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("specialite"),
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
        Medecin entity = null;
        try (Connection conn = getConnection()) {
            String SQL_FIND = "SELECT id, nom, prenom, specialite, email, telephone FROM medecins WHERE id = ?";
            PreparedStatement statement = prepareStatement(conn, SQL_FIND, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                entity = new Medecin(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("specialite"),
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
            String SQL_DELETE = "DELETE FROM `medecins` WHERE `id` = ?";
            PreparedStatement statement = prepareStatement(conn, SQL_DELETE, id);
            nbrLigne = executeUpdate(conn, statement);
            closeConnection(conn, statement);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return nbrLigne;
    }

    @Override
    public int insert(Medecin data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    @Override
    public int update(Medecin data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public int indexOf(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'indexOf'");
    }
}
