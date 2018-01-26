package services;


import db.dao.*;
import db.pojo.LoanContract;
import db.pojo.LoanRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
@Service
public class ServiceForApprovedRequestAndCreateContract {
    @Autowired
    LoanRequestDao loanRequestDao;
    @Autowired
    ManagerDao managerDao;
    @Autowired
    ClientDao clientDao;
    @Autowired
    LoanContractDao loanContractDao;

    public void approvedManager(int id_request, int user_data) throws SQLException {
        loanRequestDao.approveRequest(id_request, managerDao.getIdManagerByIdUserData(user_data));
    }

    public void approvedClientAndCreateContract(int id_request
            , String date_start
            , String date_end
            , int sum
    , int id_user_data) throws SQLException {
        loanContractDao.createAndApprove(id_request
                , 2
                , loanRequestDao.getManagerIdByIdRequest(id_request)
                , date_start
                , date_end
                , sum
        , clientDao.getIdClientByIdUserData(id_user_data));
    }

    public void cancellRequest(int id_request) throws SQLException {
    }

    public LoanRequest getRequestById(int id_request) throws SQLException {
        return loanRequestDao.getRequestById(id_request);
    }

}
