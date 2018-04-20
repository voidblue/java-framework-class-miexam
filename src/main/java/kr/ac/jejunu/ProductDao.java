package kr.ac.jejunu;

import java.sql.*;

public class ProductDao {
    private JdbcContext jdbcContext;
    public ProductDao(JdbcContext jdbcContext){
        this.jdbcContext = jdbcContext;
    }



    public Product get(Long id) throws ClassNotFoundException, SQLException {
        String sql = "select * from product where id = ?";
        Object[] params = {id};
        return jdbcContext.getContext((connection -> {
            return jdbcContext.getAndInsertStatementContext(sql, params, connection);
        }));
    }

    public Long insert(Product product) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO product(title, price) VALUES (?, ?)";
        Object[] params = {product.getTitle(), product.getPrice()};
        return jdbcContext.insertContext((connection -> {
            return jdbcContext.getAndInsertStatementContext(sql, params, connection);
        }));
    }


    public void update(Product product) {
        String sql = "UPDATE product SET title = ?, price = ? WHERE id = ?";
        Object[] params = {product.getTitle(), product.getPrice(), product.getId()};
        jdbcContext.updateAndDeleteStatmentContext(sql, params);
    }


    public void delete(Long id) {
        String sql = "DELETE FROM product WHERE id = ?";
        Object[] params = {id};
        jdbcContext.updateAndDeleteStatmentContext(sql, params);
    }

}
