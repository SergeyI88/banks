package services;

import java.sql.SQLException;

public interface ServiceCreateRequest {
    void createRequest(String id_user_data, int sum, int duration) throws SQLException;
}
