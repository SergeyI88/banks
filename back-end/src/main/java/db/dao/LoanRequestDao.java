package db.dao;



import db.pojo.LoanRequest;

import java.sql.SQLException;
import java.util.List;

public interface LoanRequestDao {
    boolean createRequest(int sum, int duration, int id_client) throws SQLException;

    void cancellRequest(int id_request) throws SQLException;

    void approveRequest(int id_request, int id_manager) throws SQLException;

    List<LoanRequest> getAllLoanRequestsForUserByIdUserData(int id_user_data) throws SQLException;

    List<LoanRequest> getAllLoanRequestsForManager() throws SQLException;
    int getManagerIdByIdRequest(int id_request) throws SQLException;

    LoanRequest getRequestById(int id_request) throws SQLException;
}
