import java.sql.*;

public class Database {
    public static void main(String[] args) throws SQLException {

        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "123456";
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println("Connected to database");

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from product");
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int price = resultSet.getInt("price");
            String category = resultSet.getString("category");
            String subCategory = resultSet.getString("sub_category");
            Integer stock = resultSet.getInt("stock");

            System.out.println(String.format("Product: %d - %s %d %s %s %d", id, name, price, category, subCategory, stock));
        }

        connection.close();
        System.out.println("Connection Closed");

    }
}
