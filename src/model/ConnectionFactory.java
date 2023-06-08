/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

/**
 *
 * @author murilodio
 */
public class ConnectionFactory {
    private static final String url = "jdbc:mysql://localhost:3306/manager";
    private static final String username = "murilo";
    private static final String password = "183729";

    private static Connection connection;

    private ConnectionFactory() {
    }

    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Registrar o driver JDBC
                Class.forName("com.mysql.jdbc.Driver");
                // Estabelecer a conexão com o banco de dados
                connection = DriverManager.getConnection(url, username, password);
                System.out.println("Conexão estabelecida!");
            } catch (ClassNotFoundException e) {
                System.out.println("Driver JDBC não encontrado!");
                e.printStackTrace();
            } catch (SQLException e) {
                System.out.println("Erro ao conectar ao banco de dados!");
                e.printStackTrace();
            }
        }
        return connection;
    }
}



//public class App {
//
//    // Configurações do banco de dados
//    private static final String URL = "jdbc:mysql://localhost:3306/manager";
//    private static final String USERNAME = "murilo";
//    private static final String PASSWORD = "183729";
//
//    public static void main(String[] args) {
//        // Passo 1: Registrar o driver JDBC
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            System.out.println("Driver JDBC não encontrado!");
//            e.printStackTrace();
//            return;
//        }
//
//        // Passo 2: Estabelecer a conexão com o banco de dados
//        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
//            System.out.println("Conexão bem-sucedida!");
//
//           
//        } catch (SQLException e) {
//            System.out.println("Erro ao conectar ao banco de dados!");
//            e.printStackTrace();
//        }
//    }
//}

