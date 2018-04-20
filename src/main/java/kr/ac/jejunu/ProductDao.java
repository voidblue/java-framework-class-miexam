package kr.ac.jejunu;

import javax.sql.DataSource;
import java.sql.*;

public class ProductDao {
    DataSource dataSource;
    public ProductDao(DataSource dataSource){
        this.dataSource = dataSource;
    }

    private Connection getConnection() throws SQLException, ClassNotFoundException {
        return dataSource.getConnection();
    }


    public Product get(Long id) throws ClassNotFoundException, SQLException {
        StatementStrategy statementStrategy = new GetStatementStarategy(id);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Product product = null;

        try {
            connection = getConnection();

            preparedStatement = statementStrategy.makeStatement(connection);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                product = new Product();
                product.setId(resultSet.getLong("id"));
                product.setTitle(resultSet.getString("title"));
                product.setPrice(resultSet.getInt("price"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(connection != null){
                try {
                    connection.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            if(resultSet != null){
                try {
                    resultSet.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

        return product;
    }



    public Long insert(Product product) throws SQLException, ClassNotFoundException {
        StatementStrategy statementStrategy = new InsertStatementStategy(product);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Long insertedId = null;
        try {
            connection = getConnection();

            preparedStatement =  statementStrategy.makeStatement(connection);

            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            insertedId = resultSet.getLong(1);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(connection != null){
                try {
                    connection.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            if(resultSet != null){
                try {
                    resultSet.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }


        return insertedId;
    }

    public void update(Product product) {
        StatementStrategy statementStrategy = new UpdateStatementStategy(product);
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();

            preparedStatement = statementStrategy.makeStatement(connection);

            preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(connection != null){
                try {
                    connection.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    public void delete(Long id) {
        StatementStrategy statementStrategy = new DeleteStatementStrategy(id);
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();

            preparedStatement = statementStrategy.makeStatement(connection);

            preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(connection != null){
                try {
                    connection.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
