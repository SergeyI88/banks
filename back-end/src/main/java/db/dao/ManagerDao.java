package db.dao;

import java.sql.SQLException;

public interface ManagerDao {
    public int getIdManagerByIdUserData(int id_user_data) throws SQLException;
}
