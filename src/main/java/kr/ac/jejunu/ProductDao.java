package kr.ac.jejunu;

import javax.sql.DataSource;
import java.sql.*;

public class ProductDao {
    private JdbcContext jdbcContext;
    public ProductDao(JdbcContext jdbcContext){
        this.jdbcContext = jdbcContext;
    }



    public Product get(Long id) throws ClassNotFoundException, SQLException {
        StatementStrategy statementStrategy = new GetStatementStarategy(id);
        return jdbcContext.getContext(statementStrategy);
    }



    public Long insert(Product product) throws SQLException, ClassNotFoundException {
        StatementStrategy statementStrategy = new InsertStatementStategy(product);
        return jdbcContext.insertContext(statementStrategy);
    }


    public void update(Product product) {
        StatementStrategy statementStrategy = new UpdateStatementStategy(product);
        jdbcContext.updateContext(statementStrategy);
    }


    public void delete(Long id) {
        StatementStrategy statementStrategy = new DeleteStatementStrategy(id);
        jdbcContext.updateContext(statementStrategy);
    }
}
