package db.dao;

import db.pojo.LoanContract;

import java.sql.SQLException;
import java.util.List;

public interface LoanContractDao {
    void createAndApprove(int id_request, int id_status, int id_manager
            , String date_start, String date_end, int sum, int id_client) throws SQLException;

    boolean payContract(int id_contract) throws SQLException;

    boolean closeContract(int id_contract) throws SQLException;

    List<LoanContract> getAllContractsByIdClient(int id_client) throws SQLException;

    List<LoanContract> getAllContractsByIdManager(int id_manager) throws SQLException;
}
