package db.dao;


import db.connections.ConnectionManager;
import db.connections.ConnectionManagerPostgres;
import db.pojo.Client;
import db.pojo.UserData;
import db.pojo.UserPersonal;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ClientDaoImpl implements ClientDao {
    private static ConnectionManager connectionManager = ConnectionManagerPostgres.getInstance();

    @Override
    public List<Client> getAll() throws SQLException {
        Connection connection = connectionManager.getConnection();
        Statement statement = connection.createStatement();

        ResultSet set = statement.executeQuery("SELECT c.id" +
                ", c.vip" +
                ",ud.id as user_data_id ," +
                "ud.login, " +
                " ud.password ," +
                " ud.date_reg ," +
                " up.id as user_personal_id, up.first_name, up.second_name, up.sex, up.birthday, up.proffesion " +
                "from client c " +
                "JOIN user_data ud on c.id_user_data = ud.id" +
                " JOIN user_personal up on up.id = ud.id_personal");

        List<Client> clientList = new ArrayList<>();
        while (set.next()) {
            UserPersonal userPersonal = new UserPersonal(set.getInt("id")
                    , set.getString("first_name"), "last"
                    , set.getString("second_name")
                    , set.getObject("birthday").toString()
                    , set.getString("sex")
                    , set.getString("proffesion")
            );
            UserData userData = new UserData(
                    set.getInt("user_data_id")
                    , userPersonal
                    , set.getString("login")
                    , set.getString("password")
                    , set.getObject("date_reg").toString()
            );

            Client client = new Client(set.getInt("id")
                    , userData
                    , set.getBoolean("vip"));
            clientList.add(client);

        }
        connection.close();
        return clientList;
    }

    @Override
    public boolean register(int id) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO " +
                "client VALUES (DEFAULT, ?, DEFAULT);");
        statement.setInt(1, id);
        int res = 0;
        res = statement.executeUpdate();
        connection.close();
        return res == 1;

    }





    @Override
    public boolean cancelRequest(String id_request) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE " +
                "loan_request SET approved = false " +
                "WHERE id = " + id_request);
        return statement.execute();
    }

    @Override
    public boolean payContract(String id_contract) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE " +
                "loan_contract SET status = " + 5 +
                "WHERE id = " + Integer.parseInt(id_contract));
        return statement.execute();
    }



    @Override
    public int getIdClientByIdUserData(int id_user_data) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT " +
                "id from client WHERE id = ?"
        );
        statement.setInt(1, id_user_data);
        ResultSet set = statement.executeQuery();
        if (set.next()) {
            return set.getInt("id");
        }
        return -1;
    }
}
