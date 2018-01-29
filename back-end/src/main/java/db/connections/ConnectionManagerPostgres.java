package db.connections;



import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionManagerPostgres implements ConnectionManager {
    ComboPooledDataSource cpds = new ComboPooledDataSource();
    private static ConnectionManager connectionManager;

    public static ConnectionManager getInstance() {
        if (connectionManager == null) {
            connectionManager = new ConnectionManagerPostgres();
        }
        return ConnectionManagerPostgres.connectionManager;
    }

    private ConnectionManagerPostgres() {
    }

    @Override
    public Connection getConnection() {
        Connection connection = null;
        try {
            cpds.setDriverClass("org.postgresql.Driver");
            cpds.setJdbcUrl("jdbc:postgresql://localhost/Loans");
            cpds.setUser("postgres");
            cpds.setPassword("root");
            cpds.setAcquireIncrement(5);
            cpds.setMaxPoolSize(1000);
            connection = cpds.getConnection();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
