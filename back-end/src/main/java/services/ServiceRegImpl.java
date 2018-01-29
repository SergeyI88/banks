package services;



import db.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class ServiceRegImpl implements ServiceReg {
    @Autowired
    ClientDao clientDao;
    @Autowired
    UserDataDao userDataDao;
    @Autowired
    UserPersonalDao userPersonalDao;

    @Override
    public boolean reg(String first_name, String last_name
            , String second_name, String birthday, String sex, String proffesion
            , String login, String password, String date_reg) throws SQLException {
        return clientDao.register(
                userDataDao.reg(
                        userPersonalDao.reg(
                                first_name, last_name, second_name, birthday, sex, proffesion)
                        , login, password, date_reg));

    }
}
