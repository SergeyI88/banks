package db.dao;


import db.connections.ConnectionManager;
import db.connections.ConnectionManagerPostgres;
import db.pojo.Client;
import db.pojo.LoanRequest;
import db.pojo.UserData;
import db.pojo.UserPersonal;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
@Component
public class LoanRequestDaoImpl implements LoanRequestDao {
    private static ConnectionManager connectionManager = ConnectionManagerPostgres.getInstance();

    @Override
    public boolean createRequest(int sum, int duration, int id_client) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO " +
                "loan_request VALUES (DEFAULT, ?, ?, ?, DEFAULT);");
        statement.setInt(1, id_client);
        statement.setInt(2, sum);
        statement.setInt(3, duration);
        statement.execute();
        connection.close();
        return true;
    }

    @Override
    public void cancellRequest(int id_request) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE loan_request " +
                "SET approved = false WHERE id = ?");
        statement.setInt(1, id_request);
        statement.execute();
    }

    @Override
    public void approveRequest(int id_request, int id_manager) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE loan_request " +
                "SET approved = true, id_manager = ? WHERE id = ?");
        statement.setInt(1, id_manager);
        statement.setInt(2, id_request);
        statement.execute();
    }


    @Override
    public List<LoanRequest> getAllLoanRequestsForUserByIdUserData(int id_user_data) throws SQLException {
        List<LoanRequest> loanRequests = new ArrayList<>();
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT" +
                "  lr.id," +
                "  lr.sum," +
                "  lr.duration," +
                "  lr.approved," +
                "  lr.id_manager," +
                "  up.first_name," +
                "  up.last_name," +
                "  up.proffesion," +
                "  up.sex," +
                "  up.birthday" +
                "  FROM loan_request lr" +
                "  INNER JOIN client c ON c.id = lr.id_client" +
                "  INNER JOIN user_data ud ON ud.id = c.id_user_data" +
                "  INNER JOIN user_personal up ON up.id = ud.id_personal" +
                "  WHERE ud.id = ?;");
        statement.setInt(1, id_user_data);
        ResultSet set = statement.executeQuery();
        while (set.next()) {
            UserPersonal userPersonal = new UserPersonal(0, set.getString("first_name")
                    , set.getString("last_name"), null, set.getString("birthday")
                    , set.getString("sex"), set.getString("proffesion"));
            UserData userData = new UserData(0, userPersonal, null, null, null);
            Client client = new Client(0, userData, false);
            LoanRequest loanRequest = new LoanRequest(set.getInt("id")
                    , client
                    , set.getInt("sum")
                    , set.getInt("duration")
                    , set.getBoolean("approved")
                    , null);
            loanRequest.setId_manager(set.getInt("id_manager"));
            loanRequests.add(loanRequest);
        }
        connection.close();
        return loanRequests;
    }

    @Override
    public List<LoanRequest> getAllLoanRequestsForManager() throws SQLException {
        List<LoanRequest> loanRequests = new ArrayList<>();
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT " +
                "lr.id, lr.sum, lr.duration, lr.approved, " +
                "up.first_name, up.last_name, up.proffesion, up.sex, up.birthday, " +
                "c.vip " +
                "FROM loan_request lr " +
                "INNER JOIN client c on c.id = lr.id_client " +
                "INNER JOIN user_data ud on ud.id = c.id_user_data " +
                "INNER JOIN user_personal up on up.id = ud.id_personal "
        );
        ResultSet set = statement.executeQuery();
        while (set.next()) {
            UserPersonal userPersonal = new UserPersonal(0, set.getString("first_name")
                    , set.getString("last_name"), null, set.getString("birthday")
                    , set.getString("sex"), set.getString("proffesion"));
            UserData userData = new UserData(0, userPersonal, null, null, null);
            Client client = new Client(0, userData, set.getBoolean("vip"));
            LoanRequest loanRequest = new LoanRequest(set.getInt("id")
                    , client, set.getInt("sum")
                    , set.getInt("duration")
                    , set.getBoolean("approved")
                    , null);
            loanRequests.add(loanRequest);
        }
        connection.close();
        return loanRequests;
    }

    @Override
    public int getManagerIdByIdRequest(int id_request) throws SQLException {
        int result = 0;
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT " +
                "id_manager " +
                "FROM loan_request  " +
                "WHERE id = ?"
        );
        statement.setInt(1, id_request);
        ResultSet set = statement.executeQuery();
        set.next();
        result = set.getInt("id_manager");
        connection.close();
        return result;
    }

    @Override
    public LoanRequest getRequestById(int id_request) throws SQLException {
        LoanRequest loanRequest = null;
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT " +
                "* " +
                "FROM loan_request  " +
                "WHERE id = ?"
        );
        statement.setInt(1, id_request);
        ResultSet set = statement.executeQuery();
        set.next();
        loanRequest = new LoanRequest(set.getInt("id")
                , set.getInt("id_client")
                , set.getInt("sum")
                , set.getInt("duration")
                , set.getBoolean("approved"), set.getInt("id_manager"));
        connection.close();
        return loanRequest;
    }
}
