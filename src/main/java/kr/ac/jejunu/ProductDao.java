package kr.ac.jejunu;

import javax.sql.DataSource;
import java.sql.*;

public class ProductDao {
    private JdbcContext jdbcContext;
    public ProductDao(JdbcContext jdbcContext){
        this.jdbcContext = jdbcContext;
    }



    public Product get(Long id) throws ClassNotFoundException, SQLException {
        return jdbcContext.getContext((connection -> {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("select * from product where id = ?");
            preparedStatement.setLong(1, id);

            return preparedStatement;
        }));
    }

    public Long insert(Product product) throws SQLException, ClassNotFoundException {
        return jdbcContext.insertContext((connection -> {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO product(title, price) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, product.getTitle());
            preparedStatement.setInt(2, product.getPrice());
            return preparedStatement;
        }));
    }


    public void update(Product product) {
        jdbcContext.updateContext((connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE product SET title = ?, price = ? WHERE id = ?");
            preparedStatement.setString(1, product.getTitle());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.setLong(3, product.getId());
            return preparedStatement;
        }));
    }


    public void delete(Long id) {
        jdbcContext.updateContext((connection -> {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM product WHERE id = ?");
            preparedStatement.setLong(1, id);
            return preparedStatement;
        }));
    }
}
