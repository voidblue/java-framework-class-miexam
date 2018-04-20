package kr.ac.jejunu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JejuProductDao extends ProductDao {

    @Override
    Connection getConnection() throws ClassNotFoundException, SQLException {
        {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost/jeju?characterEncoding=UTF-8", "root", "456111");
        }
    }
}
