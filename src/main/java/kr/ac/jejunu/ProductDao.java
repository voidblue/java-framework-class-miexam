package kr.ac.jejunu;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.*;

public class ProductDao {

    private JdbcTemplate jdbcTemplate;
    public ProductDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }


    public Product get(Long id) throws ClassNotFoundException, SQLException {
        String sql = "select * from product where id = ?";
        Object[] params = {id};
        try{
            return jdbcTemplate.queryForObject(sql, params, (rs, rowNum)->{
//            rs.next();
                Product product = new Product();
                product.setId(rs.getLong("id"));
                product.setTitle(rs.getString("title"));
                product.setPrice(rs.getInt("price"));

                return product;
//            @Override
//            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
//                return null;
//            }
            });
        }catch (EmptyResultDataAccessException e){
            return null;
        }
//        return jdbcContext.getContext((connection -> {
//            return jdbcContext.getAndInsertStatementContext(sql, params, connection);
//        }));
    }

    public Long insert(Product product) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO product(title, price) VALUES (?, ?)";
        Object[] params = {product.getTitle(), product.getPrice()};
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update((con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //???
            for(int i = 0 ; i < params.length ; i++){
                preparedStatement.setObject(i+1, params[i]);
            }

            return preparedStatement;
        }),keyHolder);
        return keyHolder.getKey().longValue();
//        return jdbcContext.insertContext((connection -> {
//            return jdbcContext.getAndInsertStatementContext(sql, params, connection);
//        }));
    }


    public void update(Product product) {
        String sql = "UPDATE product SET title = ?, price = ? WHERE id = ?";
        Object[] params = {product.getTitle(), product.getPrice(), product.getId()};
        jdbcTemplate.update(sql, params);
    }


    public void delete(Long id) {
        String sql = "DELETE FROM product WHERE id = ?";
        Object[] params = {id};
        jdbcTemplate.update(sql, params);
    }

}
