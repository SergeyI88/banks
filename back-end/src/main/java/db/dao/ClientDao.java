package db.dao;


import db.pojo.Client;

import java.sql.SQLException;
import java.util.List;


public interface ClientDao {
    List<Client> getAll() throws SQLException;
    boolean register(int id) throws SQLException;
    boolean cancelRequest(String request_id) throws SQLException;
    boolean payContract(String id) throws SQLException;
    public int getIdClientByIdUserData(int id_user_data) throws SQLException;

}
