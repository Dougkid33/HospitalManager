/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.Properties;

/**
 *
 * @author murilodio
 */
public class ConnectionFactory {

    private static final String url = "jdbc:mysql://localhost:3306/manager";
    private static final String username = "root";
    private static final String password = "1433";
    // private static final String username = "manager";
    // private static final String password = "1234";

    private static Connection connection;

    private ConnectionFactory() {
    }

    public static Connection getConnection() {
        Properties properties = new Properties();
        properties.setProperty("user", username);
        properties.setProperty("password", password);
        properties.setProperty("useSSL", "false");
        properties.setProperty("useTimezone", "true");
        properties.setProperty("serverTimezone", "UTC");
        properties.setProperty("allowPublicKeyRetrieval", "true");

        try {
            // Registrar o driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Estabelecer a conexão com o banco de dados
            connection = DriverManager.getConnection(url, properties);
            //System.out.println("Conexão estabelecida!");
            return connection;
        } catch (ClassNotFoundException e) {
            System.out.println("Driver JDBC não encontrado!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados!");
            e.printStackTrace();
        }

        return null;
    }

}
