package kr.ac.jejunu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertStatementStategy implements StatementStrategy {
    private Product product;
    public InsertStatementStategy(Product product) {
        this.product = product;
    }

    @Override
    public PreparedStatement makeStatement(Connection connection) throws SQLException {

        PreparedStatement preparedStatement =
                connection.prepareStatement("INSERT INTO product(title, price) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, product.getTitle());
        preparedStatement.setInt(2, product.getPrice());
        return preparedStatement;
    }
}
