package db.dao;



import db.connections.ConnectionManager;
import db.connections.ConnectionManagerPostgres;
import db.dao.ManagerDao;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
@Component
public class ManagerDaoImpl implements ManagerDao {
    private static ConnectionManager connectionManager = ConnectionManagerPostgres.getInstance();


    @Override
    public int getIdManagerByIdUserData(int id_user_data) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT " +
                "id from manager WHERE id_user_data = ?"
        );
        statement.setInt(1, id_user_data);
        ResultSet set = statement.executeQuery();
        if (set.next()) {
            return set.getInt("id");
        }
        return -1;
    }
}
