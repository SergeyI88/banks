package services;

import db.pojo.LoanRequest;

import java.sql.SQLException;

public interface ServiceForApprovedRequestAndCreateContract {
    void approvedManager(int id_request, int user_data) throws SQLException;

    void approvedClientAndCreateContract(int id_request
            , String date_start
            , String date_end
            , int sum
            , int id_user_data) throws SQLException;

    void cancellRequest(int id_request) throws SQLException;

    LoanRequest getRequestById(int id_request) throws SQLException;
}
