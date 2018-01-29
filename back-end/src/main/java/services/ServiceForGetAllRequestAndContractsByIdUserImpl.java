package services;

import db.dao.*;
import db.pojo.LoanContract;
import db.pojo.LoanRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
@Service
public class ServiceForGetAllRequestAndContractsByIdUserImpl implements ServiceForGetAllRequestAndContractsByIdUser {
    @Autowired
    ClientDao clientDao;
    @Autowired
    LoanRequestDao loanRequestDao;
    @Autowired
    LoanContractDao loanContractDao;
    @Autowired
    UserDataDao userDataDao;
    @Autowired
    ManagerDao managerDao;
    @Autowired
    UserPersonalDao userPersonalDao;

    @Override
    public List<LoanRequest> getAllRequestByIdUser(Integer idUser) throws SQLException {
        return loanRequestDao.getAllLoanRequestsForUserByIdUserData(idUser);
    }

    @Override
    public List<LoanContract> getAllContractsByIdUser(Integer idUser) throws SQLException {
        return loanContractDao.getAllContractsByIdClient(idUser);
    }

    @Override
    public List<LoanRequest> getAllRequests() throws SQLException {
        return loanRequestDao.getAllLoanRequestsForManager();
    }

    @Override
    public List<LoanContract> getAllContractsByIdManager(Integer idUser) throws SQLException {

        return loanContractDao.getAllContractsByIdManager(
                managerDao.getIdManagerByIdUserData(idUser));
    }
}
