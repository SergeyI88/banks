package services;

import java.sql.SQLException;

public interface ServiceReg {
    boolean reg(String first_name, String last_name
            , String second_name, String birthday, String sex, String proffesion
            , String login, String password, String date_reg) throws SQLException;
}
