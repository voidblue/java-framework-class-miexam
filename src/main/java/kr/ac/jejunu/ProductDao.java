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
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Product product = null;

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement("select * from product where id = ?");
            preparedStatement.setLong(1, id);

            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            product = new Product();
            product.setId(resultSet.getLong("id"));
            product.setTitle(resultSet.getString("title"));
            product.setPrice(resultSet.getInt("price"));
        }catch (SQLException e){
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
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Long insertedId = null;
        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement("INSERT INTO product(title, price) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, product.getTitle());
            preparedStatement.setInt(2, product.getPrice());

            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            insertedId = resultSet.getLong(1);
        }catch (SQLException e){
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
}
