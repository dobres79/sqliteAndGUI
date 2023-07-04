import org.sqlite.JDBC;

import java.sql.*;
import java.util.*;

public class DbHandler {
    // Константа, в которой хранится адрес подключения
    private static final String CON_STR = "jdbc:sqlite:C:/sqlite/testSQLite.db";

    // Используем шаблон одиночка, чтобы не плодить множество
    // экземпляров класса DbHandler
    private static DbHandler instance = null;

    public static synchronized DbHandler getInstance() throws SQLException {
        if (instance == null)
            instance = new DbHandler();
        return instance;
    }

    // Объект, в котором будет храниться соединение с БД
    private Connection connection;

    private DbHandler() throws SQLException {
        // Регистрируем драйвер, с которым будем работать
        // в нашем случае Sqlite
        DriverManager.registerDriver(new JDBC());
        // Выполняем подключение к базе данных
        this.connection = DriverManager.getConnection(CON_STR);
    }

    public List<Map <String, Object>> getAllData() {
        List<Map <String, Object>> allData = new ArrayList<>();
        // Statement используется для того, чтобы выполнить sql-запрос
        try (Statement statement = this.connection.createStatement()) {
            // В данный список будем загружать наши продукты, полученные из БД

            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            // Проходимся по нашему resultSet и заносим данные в products

            while (resultSet.next()) {
              Map<String, Object>  a = new HashMap<>();
              a.put("id", resultSet.getInt("id"));
              a.put("username", resultSet.getString("username"));
              a.put("password", resultSet.getString("password"));
              allData.add(a);

            }
            // Возвращаем наш список
            return allData;

        } catch (SQLException e) {
            e.printStackTrace();
            // Если произошла ошибка - возвращаем пустую коллекцию
            return Collections.emptyList();
        }
    }

}

