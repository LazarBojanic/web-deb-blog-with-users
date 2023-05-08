package rs.raf.domaci6lazarbojanic11621rn.database;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.sql.Connection;
import java.sql.SQLException;

public class BlogDatabase {
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/blog";
    private static final String JDBC_USER = "postgres";
    private static final String JDBC_PASSWORD = "1234";
    private DataSource dataSource;

    private static volatile BlogDatabase instance;

    private BlogDatabase() {
        PoolProperties p = new PoolProperties();
        p.setDriverClassName("org.postgresql.Driver");
        p.setUrl(JDBC_URL);
        p.setUsername(JDBC_USER);
        p.setPassword(JDBC_PASSWORD);
        dataSource = new DataSource();
        dataSource.setPoolProperties(p);
    }

    public static BlogDatabase getInstance() {
        if (instance == null) {
            synchronized (BlogDatabase.class) {
                if (instance == null) {
                    instance = new BlogDatabase();
                }
            }
        }
        return instance;
    }

    public void closeConnection() {
        dataSource.close();
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
