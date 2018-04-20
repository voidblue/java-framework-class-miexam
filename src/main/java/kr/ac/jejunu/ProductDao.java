package kr.ac.jejunu;

import java.sql.*;

public class ProductDao {
    ConnectionMaker connectionMaker;
    public ProductDao(ConnectionMaker connectionMaker){
        this.connectionMaker = connectionMaker;
    }

    private Connection getConnection() throws SQLException, ClassNotFoundException {
        return connectionMaker.getConnection();
    }


    public Product get(Long id) throws ClassNotFoundException, SQLException {
        Connection connection = getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("select * from product where id = ?");
        preparedStatement.setLong(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        Product product = new Product();
        product.setId(resultSet.getLong("id"));
        product.setTitle(resultSet.getString("title"));
        product.setPrice(resultSet.getInt("price"));

        //자원을 해지한다.
        resultSet.close();
        preparedStatement.close();
        connection.close();

        return product;
    }



    public Long insert(Product product) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO product(title, price) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, product.getTitle());
        preparedStatement.setInt(2, product.getPrice());

        preparedStatement.executeUpdate();

        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        resultSet.next();
        Long insertedId = resultSet.getLong(1);

        //자원을 해지한다.
        resultSet.close();
        preparedStatement.close();
        connection.close();

        return insertedId;
    }
}
