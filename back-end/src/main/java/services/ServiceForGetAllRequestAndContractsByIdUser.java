package services;

import db.pojo.LoanContract;
import db.pojo.LoanRequest;

import java.sql.SQLException;
import java.util.List;

public interface ServiceForGetAllRequestAndContractsByIdUser {
    List<LoanRequest> getAllRequestByIdUser(Integer idUser) throws SQLException;

    List<LoanContract> getAllContractsByIdUser(Integer idUser) throws SQLException;

    List<LoanRequest> getAllRequests() throws SQLException;

    List<LoanContract> getAllContractsByIdManager(Integer idUser) throws SQLException;
}
