package db.dao;


import db.connections.ConnectionManager;
import db.connections.ConnectionManagerPostgres;
import db.pojo.*;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class LoanContractDaoImpl implements LoanContractDao {
    private static ConnectionManager connectionManager = ConnectionManagerPostgres.getInstance();

    @Override
    public void createAndApprove(int id_request, int id_status, int id_manager
            , String date_start, String date_end, int sum, int id_client) throws SQLException {

        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO " +
                "loan_contract VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?);");
        statement.setInt(1, id_request);
        statement.setInt(2, id_status);
        statement.setInt(3, id_manager);
        statement.setString(4, date_start);
        statement.setString(5, date_end);
        statement.setInt(6, sum);
        statement.setInt(7, id_client);
        statement.execute();
    }

    @Override
    public boolean payContract(int id_contract) throws SQLException {

        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE " +
                "loan_contract SET status = 1 WHERE id = ?);");
        statement.setInt(1, id_contract);

        connection.close();
        return statement.execute();
    }

    @Override
    public boolean closeContract(int id_contract) throws SQLException {

        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE " +
                "loan_contract SET status = 4 WHERE id = ?);");
        statement.setInt(1, id_contract);

        connection.close();
        return statement.execute();
    }

    @Override
    public List<LoanContract> getAllContractsByIdClient(int id_client) throws SQLException {
        List<LoanContract> list = new ArrayList<>();
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT " +
                "lc.id, lc.status, lc.id_manager, lc.date_start, lc.date_end, lc.sum, lc.id_request, " +
                "up.first_name, up.last_name " +
                "FROM loan_contract lc " +
                "INNER JOIN manager m on m.id = lc.id_manager " +
                "left JOIN user_data ud on ud.id = m.id_user_data " +
                "left JOIN user_personal up on up.id = ud.id_personal " +
                "WHERE lc.id_client = ?;");
        statement.setInt(1, id_client);
        ResultSet set = statement.executeQuery();
        while (set.next()) {
            UserPersonal userPersonal = new UserPersonal(0, set.getString("first_name")
                    , set.getString("last_name")
                    , null, null, null, null);
            UserData userData = new UserData(0, userPersonal,
                    null, null, null);
            Manager manager = new Manager(set.getInt("id_manager"), userData);
            LoanContract loanContract = new LoanContract(
                    set.getInt("id"), set.getInt("id_request"),
                    set.getString("status"), manager,
                    set.getString("date_start"), set.getString("date_end"),
                    set.getInt("sum"), null
            );
            list.add(loanContract);
        }
        connection.close();
        return list;
    }

    @Override
    public List<LoanContract> getAllContractsByIdManager(int id_manager) throws SQLException {
        List<LoanContract> list = new ArrayList<>();
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT " +
                "lc.id, lc.status, lc.date_start, lc.date_end, lc.sum, lc.id_client, " +
                "up.first_name, up.last_name " +
                "FROM loan_contract lc INNER JOIN " +
                "user_data ud on ud.id = lc.id_manager " +
                "INNER JOIN user_personal up on up.id = ud.id_personal" +
                "WHERE lc.id_manager = ?");
        statement.setInt(1, id_manager);
        ResultSet set = statement.executeQuery();
        while (set.next()) {
            UserPersonal userPersonal = new UserPersonal(0, set.getString("first_name")
                    , set.getString("last_name")
                    , null, null, null, null);
            UserData userData = new UserData(0, userPersonal,
                    null, null, null);
            Client client = new Client(0, userData, false);
            LoanContract loanContract = new LoanContract(
                    set.getInt("id"), null,
                    set.getString("status"), null,
                    set.getString("date_start"), set.getString("date_end"),
                    set.getInt("sum"), client
            );
            list.add(loanContract);
        }
        connection.close();
        return list;
    }
}
