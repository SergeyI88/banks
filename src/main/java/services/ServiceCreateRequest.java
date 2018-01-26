package services;



import db.dao.ClientDao;
import db.dao.ClientDaoImpl;
import db.dao.LoanRequestDao;
import db.dao.LoanRequestDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
@Service
public class ServiceCreateRequest {

    ClientDao clientDao;
    LoanRequestDao loanRequestDao;

    public void createRequest(String id_user_data, int sum, int duration) throws SQLException {
        loanRequestDao = new LoanRequestDaoImpl();
        clientDao = new ClientDaoImpl();
        loanRequestDao.createRequest(sum
                , duration
                , clientDao.getIdClientByIdUserData(
                        Integer.parseInt(id_user_data)));
    }

    public ClientDao getClientDao() {
        return clientDao;
    }
    @Autowired
    public void setClientDao(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    public LoanRequestDao getLoanRequestDao() {
        return loanRequestDao;
    }
    @Autowired
    public void setLoanRequestDao(LoanRequestDao loanRequestDao) {
        this.loanRequestDao = loanRequestDao;
    }
}
