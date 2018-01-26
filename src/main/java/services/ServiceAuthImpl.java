package services;



import db.dao.UserDataDao;
import db.dao.UserDataDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
@Service
public class ServiceAuthImpl implements ServiceAuth{

    UserDataDao userDataDao;

    public UserDataDao getUserDataDao() {
        return userDataDao;
    }
    @Autowired
    public void setUserDataDao(UserDataDao userDataDao) {
        this.userDataDao = userDataDao;
    }

    public int auth(String login, String password) throws SQLException {
        userDataDao = new UserDataDaoImpl();
        return userDataDao.auth(login, password);
    }

    @Override
    public int getIdUserDataByLogin(String username) throws SQLException {
        return userDataDao.getIdByName(username);
    }
}
