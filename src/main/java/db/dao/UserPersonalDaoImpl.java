package db.dao;


import db.connections.ConnectionManager;
import db.connections.ConnectionManagerPostgres;
import db.dao.UserPersonalDao;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
@Component
public class UserPersonalDaoImpl implements UserPersonalDao {
    private static ConnectionManager connectionManager = ConnectionManagerPostgres.getInstance();

    @Override
    public int reg(String first_name
            , String last_name, String second_name, String birthday
            , String sex, String proffesion) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO " +
                        "user_personal VALUES (DEFAULT , ?, ?, ?, ?, ?, ?);"
        );
        statement.setString(1, first_name);
        statement.setString(2, last_name);
        statement.setString(3, second_name);
        statement.setString(4, birthday);
        statement.setString(5, sex);
        statement.setString(6, proffesion);
        statement.execute();

        Connection connection2 = connectionManager.getConnection();
        PreparedStatement statement1 = connection2.prepareStatement("SELECT id as id FROM " +
                "user_personal WHERE first_name =  ? AND last_name = ? AND second_name = ?");
        statement1.setString(1, first_name);
        statement1.setString(2, last_name);
        statement1.setString(3, second_name);
        ResultSet set = statement1.executeQuery();
        if (set.next()) {
            int id = set.getInt("id");
            connection.close();
            return id;
        }
       connection.close();
        return -1;

    }


//    public UserPersonal getManagerByIdUserData (int id_user_data) throws SQLException {
//        Connection connection = connectionManager.getConnection();
//        PreparedStatement statement = connection.prepareStatement(
//                "(INSERT INTO " +
//                        "user_personal VALUES (DEFAULT , ?, ?, ?, ?, ?, ?));"
//        );
//        UserPersonal userPersonal = new UserPersonal();
//        return userPersonal;
//    }
}
