import java.math.BigDecimal;
import java.sql.*;

public class Database {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123456";

    public static void main(String[] args) throws SQLException {
        InsertProduct();
        getAllProduct();
        System.out.println("Connection Closed");
    }

    private static void InsertProduct() throws SQLException {
        Product product = new Product();
        product.setName("a");
        product.setCategory("small");
        product.setPrice(20);
        product.setStatus("in stock");
        product.setStock(2);
        String sql = "insert into product (name, price, category, status, stock) values (?, ?, ?, ?, ?)";
        try (var connection = DriverManager.getConnection(URL, USER, PASSWORD);) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, product.getName());
            statement.setInt(2, product.getPrice());
            statement.setString(3, product.getCategory());
            statement.setString(4, product.getStatus());
            statement.setInt(5, product.getStock());

            var result = statement.executeUpdate();
            System.out.println(result);
        }
    }

    private static void getAllProduct() throws SQLException {

        try (var connection = DriverManager.getConnection(URL, USER, PASSWORD);) {
            System.out.println("Connected to database");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from product");
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getInt("price"));
                product.setCategory(resultSet.getString("category"));
                product.setStock(resultSet.getInt("stock"));

                System.out.println(String.format("Product: %d - %s %d %s %d", product.getId(), product.getName(), product.getPrice(), product.getCategory(), product.getStock()));
            }
        }
    }
}
