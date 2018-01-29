package services;

import java.sql.SQLException;

public interface ServiceAuth {

    public int auth(String login, String password) throws SQLException;

    int getIdUserDataByLogin(String username) throws SQLException;
}
