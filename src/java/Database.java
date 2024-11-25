import javax.swing.plaf.nimbus.State;
import java.math.BigDecimal;
import java.sql.*;
import java.util.Optional;

public class Database {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123456";

    public static void main(String[] args) throws SQLException {
        Product product = new Product();
//        product.setName("abcd");
//        product.setPrice(123);
//        product.setCategory("hardware");
//        product.setStatus("in stock");
//        product.setStock(2);
//        InsertProduct(product);
        displayProductById(1);
//        deleteProductById(115);
//        getAllProduct();
//        UpdateProduct();
//        Optional<Product> optProduct = findProductById(5);
//        if (optProduct.isPresent()) {
//            Product product = optProduct.get();
//            product.setCategory("smartphone");
//            updateProduct(product);
//            findProductById(5);
//        } else {
//            System.out.println("Product Not Found");
//        }
//        System.out.println("Connection Closed");

    }

    private static void InsertProduct(Product product) throws SQLException {
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

    private static void updateProduct(Product product) throws SQLException {
        String sql = "update product set name = ?, price = ?, category = ?, status = ?, stock = ? where id = ?";
        try (var connection = DriverManager.getConnection(URL, USER, PASSWORD);) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, product.getName());
            statement.setInt(2, product.getPrice());
            statement.setString(3, product.getCategory());
            statement.setString(4, product.getStatus());
            statement.setInt(5, product.getStock());
            statement.setInt(6, product.getId());

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

    private static Optional<Product> findProductById(Integer id) throws SQLException {

        try (var connection = DriverManager.getConnection(URL, USER, PASSWORD);) {
            System.out.println("Connected to database");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from product where id = " + id);
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getInt("price"));
                product.setCategory(resultSet.getString("category"));
                product.setStatus(resultSet.getString("status"));
                product.setStock(resultSet.getInt("stock"));

                return Optional.of(product);
            }
        }

        return Optional.empty();
    }

    private static boolean deleteProductById(Integer id) throws SQLException {

        try (var connection = DriverManager.getConnection(URL, USER, PASSWORD);) {
            System.out.println("Connected to database");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("delete from product where id = " + id);
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getInt("price"));
                product.setCategory(resultSet.getString("category"));
                product.setStatus(resultSet.getString("status"));
                product.setStock(resultSet.getInt("stock"));

                return true;
            }
        }

        return false;
    }

    private static void displayProductById(Integer id) throws SQLException {

        try (var connection = DriverManager.getConnection(URL, USER, PASSWORD);) {
            System.out.println("Connected to database");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from product where id = " + id);
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getInt("price"));
                product.setCategory(resultSet.getString("category"));
                product.setStatus(resultSet.getString("status"));
                product.setStock(resultSet.getInt("stock"));

                System.out.println(String.format("Product %n Id = %d %n Name = %s %n Price = %d %n Category = %s %n Status = %s %n Stock = %d", product.getId(), product.getName(),product.getPrice(), product.getCategory(), product.getStatus(), product.getStock()));

            }
        }
    }

}
