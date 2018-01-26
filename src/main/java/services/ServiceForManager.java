package services;

import db.dao.*;
import db.pojo.Manager;
import db.pojo.UserData;
import db.pojo.UserPersonal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceForManager {
    @Autowired
    private static ManagerDao managerDao;
    @Autowired
    private static UserDataDao userDataDao;
    @Autowired
    private static UserPersonalDao userPersonalDao;

    public Manager getManager(int id_manager) {

        return new Manager(id_manager, 1);
    }
}
